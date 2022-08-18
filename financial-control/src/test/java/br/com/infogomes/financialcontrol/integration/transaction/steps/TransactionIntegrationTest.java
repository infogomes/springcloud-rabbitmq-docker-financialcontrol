package br.com.infogomes.financialcontrol.integration.transaction.steps;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.repositories.TransactionRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TransactionIntegrationTest {

	@Autowired
	TransactionRepository repository;

	Transaction trans;

	@Before
	public void setUp() {
		trans = new Transaction();
		trans.setDescription("Compra Notebook");
		trans.setTransDate(LocalDate.now());
		trans.setTransValue(BigDecimal.TEN);
	}

	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@When("the transaction try to save")
	public void the_transaction_try_to_save() {
		trans.setDescription(null);
	}

	@Then("receive a exception")
	public void receive_a_exception() {
		assertThrows(ConstraintViolationException.class, () -> repository.save(trans));
	}

}
