package br.com.infogomes.financialcontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infogomes.financialcontrol.entities.Category;
import br.com.infogomes.financialcontrol.repositories.CategoryRepository;
import br.com.infogomes.financialcontrol.service.exceptions.ObjectExistsException;
import br.com.infogomes.financialcontrol.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category save(Category obj) {

		if (existsByDescriptionIgnoreCase(obj.getDescription())) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Category.class.getName());
		}
		return repository.save(obj);
	}

	public Category findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public Category update(Category obj) {

		findById(obj.getId());
		return repository.save(obj);
	}

	public void deleteById(Long id) {
		findById(id);
		repository.deleteById(id);
	}

	public boolean existsByDescriptionIgnoreCase(String description) {
		return repository.existsByDescriptionIgnoreCase(description);
	}

}
