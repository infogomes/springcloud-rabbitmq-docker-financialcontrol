package br.com.infogomes.financialcontrol.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infogomes.financialcontrol.entities.Transaction;
import br.com.infogomes.financialcontrol.entities.enuns.TipoTransaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByTipoTransaction(TipoTransaction tipoTransaction);

	boolean existsByDescriptionIgnoreCaseAndTransDateGreaterThanEqualAndTransDateLessThan(String description,
			LocalDate startDate, LocalDate endDate);

	Optional<Transaction> findByIdAndTipoTransactionEquals(Long id, TipoTransaction expense);

	List<Transaction> findByDescriptionContainingAndTipoTransactionEquals(String description, TipoTransaction income);

	Page<Transaction> findByTipoTransactionEqualsAndTransDateGreaterThanEqualAndTransDateLessThan(
			TipoTransaction income, LocalDate of, LocalDate of2, Pageable pageable);

}
