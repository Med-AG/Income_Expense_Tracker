package com.smapelle.expense_tracker.service.stats;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.smapelle.expense_tracker.dto.GraphDTO;
import com.smapelle.expense_tracker.dto.StatsDTO;
import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.entity.Income;
import com.smapelle.expense_tracker.repository.ExpenseRepository;
import com.smapelle.expense_tracker.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
	
	private final IncomeRepository incomeRepository;
	
	private final ExpenseRepository expenseRepository;

	@Override
	public GraphDTO getChartData() {
		
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusDays(30);
		
		GraphDTO graphDTO = new GraphDTO();
		graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
		graphDTO.setExpenselList(expenseRepository.findByDateBetween(startDate, endDate));
		
		return graphDTO;
	}
	
	@Override
	public StatsDTO getStats() {
		double totalIncome = incomeRepository.sumAllAmounts();
		double totalExpense = expenseRepository.sumAllAmounts();
		
		Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();
		Optional<Income> optionalIncome = incomeRepository.findFirstByOrderByDateDesc();
		
		StatsDTO statsDTO = new StatsDTO();
		statsDTO.setTotalExpense(totalExpense);
		//other way using functionnal programming
		statsDTO.setTotalIncome(incomeRepository.findAll().stream().mapToDouble(value -> value.getAmount()).sum());
		
		if (optionalIncome.isPresent()) {
			statsDTO.setLastIncome(optionalIncome.get());
		}
		if (optionalExpense.isPresent()) {
			statsDTO.setLastExpense(optionalExpense.get());
		}
		
		statsDTO.setBalance(totalIncome - totalExpense);
		
		OptionalDouble minIncome = incomeRepository.findAll().stream().mapToDouble(income -> income.getAmount()).min();
		OptionalDouble maxIncome = incomeRepository.findAll().stream().mapToDouble(income -> income.getAmount()).max();
		
		OptionalDouble minExpense = expenseRepository.findAll().stream().mapToDouble(expense -> expense.getAmount()).min();
		OptionalDouble maxExpense = expenseRepository.findAll().stream().mapToDouble(expense -> expense.getAmount()).max();
		
		statsDTO.setMaxExpense(maxExpense.getAsDouble());
		statsDTO.setMinExpense(minExpense.getAsDouble());
		
		statsDTO.setMaxIncome(maxIncome.getAsDouble());
		statsDTO.setMinIncome(minIncome.getAsDouble());
		
		return statsDTO;
	}
	
	
	
}
