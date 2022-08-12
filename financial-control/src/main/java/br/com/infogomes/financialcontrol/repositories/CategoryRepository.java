package br.com.infogomes.financialcontrol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.infogomes.financialcontrol.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	boolean existsByDescriptionIgnoreCase(String description);

}
