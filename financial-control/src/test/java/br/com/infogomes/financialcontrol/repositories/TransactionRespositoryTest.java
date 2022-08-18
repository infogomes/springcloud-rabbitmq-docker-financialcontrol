package br.com.infogomes.financialcontrol.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.entities.enuns.TipoTransaction;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionRespositoryTest {

	@Autowired
	TransactionRepository repository;

	@BeforeEach
	public void setUp() {
		Transaction transaction = new Transaction();
		transaction.setDescription("Compra Notebook");
		transaction.setTransDate(LocalDate.now());
		transaction.setTransValue(BigDecimal.TEN);
	}

	@AfterEach
	public void tearDown() {
		repository.deleteAll();
	}

	@Test()
	public void saveTransaction() {
		Transaction transaction = new Transaction();
		transaction.setDescription("Compra Notebook");
		transaction.setTransDate(LocalDate.now());
		transaction.setTransValue(BigDecimal.TEN);
		transaction.setTipoTransaction(TipoTransaction.EXPENSE);

		Transaction trans = repository.save(transaction);

		assertNotNull(trans);
	}

	@Test()
	public void testSaveInvalidTransaction() {
		Transaction transaction = new Transaction();
		transaction.setDescription("Compra Notebook");
		transaction.setTransDate(LocalDate.now());
		transaction.setTransValue(BigDecimal.TEN);

		assertThrows(ConstraintViolationException.class, () -> repository.save(transaction));
	}
}
