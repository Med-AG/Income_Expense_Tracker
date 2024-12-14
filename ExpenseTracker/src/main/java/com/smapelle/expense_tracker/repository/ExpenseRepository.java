package com.smapelle.expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.entity.Income;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
	
	@Query(value = "select sum(e.amount) from expense e", nativeQuery = true)
	double sumAllAmounts();
	
	Optional<Expense> findFirstByOrderByDateDesc();
	
}
