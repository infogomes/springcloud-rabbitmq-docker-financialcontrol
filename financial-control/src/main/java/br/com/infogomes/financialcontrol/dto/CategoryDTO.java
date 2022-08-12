package br.com.infogomes.financialcontrol.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.service.validation.CategoryInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CategoryInsert
public class CategoryDTO {

	private Long id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String description;
	private Set<Transaction> transaction;

}
