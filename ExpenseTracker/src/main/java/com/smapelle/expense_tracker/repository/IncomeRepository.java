package com.smapelle.expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "select sum(i.amount) from income i", nativeQuery = true)
	double sumAllAmounts();
	
	Optional<Income> findFirstByOrderByDateDesc();
	
}
