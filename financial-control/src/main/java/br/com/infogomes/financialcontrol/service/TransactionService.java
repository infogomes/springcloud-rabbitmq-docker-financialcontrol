package br.com.infogomes.financialcontrol.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Transaction insertIncome(Transaction obj) {
		obj.setTipoTransaction(TipoTransaction.INCOME);
		if (existsTransaction(obj)) {
			throw new ObjectExistsException("Objeto já existe no mês informado! Tipo: " + Transaction.class.getName());
		}
		return repository.save(obj);
	}

	public Transaction findIncome(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
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

}
