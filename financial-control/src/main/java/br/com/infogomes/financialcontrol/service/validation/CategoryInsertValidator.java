package br.com.infogomes.financialcontrol.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.infogomes.financialcontrol.dto.CategoryDTO;
import br.com.infogomes.financialcontrol.resources.exceptions.FieldMessage;
import br.com.infogomes.financialcontrol.service.CategoryService;

public class CategoryInsertValidator implements ConstraintValidator<CategoryInsert, CategoryDTO> {

	@Autowired
	CategoryService service;

	@Override
	public void initialize(CategoryInsert ann) {
	}

	@Override
	public boolean isValid(CategoryDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (service.existsByDescriptionIgnoreCase(value.getDescription())) {
			list.add(new FieldMessage("description", "Categoria já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

}
