package br.com.infogomes.financialcontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.infogomes.financialcontrol.entities.enuns.TipoTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String description;
	@NotNull(message="Preenchimento obrigatório")
	private BigDecimal transValue;
	@NotNull(message="Preenchimento obrigatório")
	private LocalDate transDate;
	private TipoTransaction tipoTransaction;
}
