package br.com.infogomes.financialcontrol.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.infogomes.financialcontrol.dto.CategoryDTO;
import br.com.infogomes.financialcontrol.entities.Category;
import br.com.infogomes.financialcontrol.service.CategoryService;

@RestController
@RequestMapping(value = "category")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = service.findAll();
		List<CategoryDTO> listDto = list.stream().map(obj -> {
			var dto = new CategoryDTO();
			BeanUtils.copyProperties(obj, dto);
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO objDto) {
		var obj = new Category();
		BeanUtils.copyProperties(objDto, obj);
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

//	@GetMapping(value = "receitas/{id}")
//	public ResponseEntity<CategoryDTO> find(@PathVariable Long id) {
//		Category obj = service.findIncome(id);
//		var dto = new CategoryDTO();
//		BeanUtils.copyProperties(obj, dto);
//		return ResponseEntity.ok().body(dto);
//	}
//
//	@PutMapping(value = "receitas/{id}")
//	public ResponseEntity<Void> updateIncome(@Valid @RequestBody CategoryDTO objDto, @PathVariable Long id) {
//		var obj = new Category();
//		objDto.setId(id);
//		BeanUtils.copyProperties(objDto, obj);
//		obj = service.updateIncome(obj);
//		return ResponseEntity.noContent().build();
//	}
//
//	@DeleteMapping(value = "receitas/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		service.deleteIncome(id);
//		return ResponseEntity.noContent().build();
//	}

}
