package br.com.infogomes.financialcontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.infogomes.financialcontrol.entities.enuns.TipoTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	private Long id;
	private String description;
	private BigDecimal transValue;
	private LocalDate transDate;
	private TipoTransaction tipoTransaction;
}
