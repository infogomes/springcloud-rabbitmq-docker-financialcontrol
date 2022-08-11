package br.com.infogomes.financialcontrol.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.infogomes.financialcontrol.dto.TransactionDTO;
import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.service.TransactionService;

@RestController
@RequestMapping()
public class TransactionResource {

	@Autowired
	private TransactionService service;

	@GetMapping(value = "receitas")
	public ResponseEntity<List<TransactionDTO>> findAllIncome() {
		List<Transaction> list = service.findAllIncomes();
		List<TransactionDTO> listDto = list.stream().map(obj -> {
			var dto = new TransactionDTO();
			BeanUtils.copyProperties(obj, dto);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping(value = "receitas")
	public ResponseEntity<Void> insertIncome(@Valid @RequestBody TransactionDTO objDto) {
		var obj = new Transaction();
		BeanUtils.copyProperties(objDto, obj);
		obj = service.insertIncome(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "receitas/{id}")
	public ResponseEntity<TransactionDTO> find(@PathVariable Long id) {
		Transaction obj = service.findIncome(id);
		var dto = new TransactionDTO();
		BeanUtils.copyProperties(obj, dto);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping(value = "receitas/{id}")
	public ResponseEntity<Void> updateIncome(@Valid @RequestBody TransactionDTO objDto, @PathVariable Long id) {
		var obj = new Transaction();
		objDto.setId(id);
		BeanUtils.copyProperties(objDto, obj);
		obj = service.updateIncome(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "receitas/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteIncome(id);
		return ResponseEntity.noContent().build();
	}

}