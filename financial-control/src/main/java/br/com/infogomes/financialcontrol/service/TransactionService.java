package br.com.infogomes.financialcontrol.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.entities.enuns.TipoTransaction;
import br.com.infogomes.financialcontrol.repositories.TransactionRepository;
import br.com.infogomes.financialcontrol.service.exceptions.ObjectExistsException;
import br.com.infogomes.financialcontrol.service.exceptions.ObjectNotFoundException;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository repository;

	public List<Transaction> findAllIncomes() {
		return repository.findByTipoTransaction(TipoTransaction.INCOME);
	}
	
	public Page<Transaction> findIncomesByMonthAndYearPage(Integer year, Integer month, Pageable pageable) {
		return findByMonthAndYearAndTipoTransaction(year, month, TipoTransaction.INCOME, pageable);
	}

	public Transaction insertIncome(Transaction obj) {
		obj.setTipoTransaction(TipoTransaction.INCOME);
		if (existsTransaction(obj)) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Transaction.class.getName());
		}
		return repository.save(obj);
	}

	public Transaction findIncome(Long id) {
		return repository.findByIdAndTipoTransactionEquals(id, TipoTransaction.INCOME)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Transaction.class.getName()));
	}

	public Transaction updateIncome(Transaction obj) {
		obj.setTipoTransaction(TipoTransaction.INCOME);
		findIncome(obj.getId());
		if (existsTransaction(obj)) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Transaction.class.getName());
		}
		return repository.save(obj);
	}

	public void deleteIncome(Long id) {
		findIncome(id);
		repository.deleteById(id);
	}

	public List<Transaction> findAllExpenses() {
		return repository.findByTipoTransaction(TipoTransaction.EXPENSE);
	}
	
	public Page<Transaction> findExpensesByMonthAndYearPage(Integer year, Integer month, Pageable pageable) {
		return findByMonthAndYearAndTipoTransaction(year, month, TipoTransaction.EXPENSE, pageable);
	}

	public Transaction insertExpense(Transaction obj) {
		obj.setTipoTransaction(TipoTransaction.EXPENSE);
		if (existsTransaction(obj)) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Transaction.class.getName());
		}
		return repository.save(obj);
	}

	public Transaction findExpense(Long id) {
		return repository.findByIdAndTipoTransactionEquals(id, TipoTransaction.EXPENSE)
				.orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + Transaction.class.getName()));
	}

	public Transaction updateExpense(Transaction obj) {
		obj.setTipoTransaction(TipoTransaction.EXPENSE);
		findExpense(obj.getId());
		if (existsTransaction(obj)) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Transaction.class.getName());
		}
		return repository.save(obj);
	}

	public void deleteExpense(Long id) {
		findExpense(id);
		repository.deleteById(id);
	}

	public boolean existsByDescriptionIgnoreCaseAndTransDateGreaterThanEqualAndTransDateLessThan(String description,
			LocalDate startDate, LocalDate endDate) {
		return repository.existsByDescriptionIgnoreCaseAndTransDateGreaterThanEqualAndTransDateLessThan(description,
				startDate, endDate);
	}

	private boolean existsTransaction(Transaction obj) {
		return existsByDescriptionIgnoreCaseAndTransDateGreaterThanEqualAndTransDateLessThan(obj.getDescription(),
				LocalDate.of(obj.getTransDate().getYear(), obj.getTransDate().getMonthValue(), 1),
				LocalDate.of(obj.getTransDate().getYear(), obj.getTransDate().getMonthValue() + 1, 1));
	}

	public List<Transaction> findExpensesByDescriptionContaining(String description) {
		return repository.findByDescriptionContainingAndTipoTransactionEquals(description, TipoTransaction.EXPENSE);
	}

	public List<Transaction> findIncomesByDescriptionContaining(String description) {
		return repository.findByDescriptionContainingAndTipoTransactionEquals(description, TipoTransaction.INCOME);
	}


	private Page<Transaction> findByMonthAndYearAndTipoTransaction(Integer year, Integer month, TipoTransaction tipoTransaction,
			Pageable pageable) {
		return repository.findByTipoTransactionEqualsAndTransDateGreaterThanEqualAndTransDateLessThan(
				TipoTransaction.INCOME, LocalDate.of(year, month, 1), LocalDate.of(year, month + 1, 1), pageable);
	}

}
