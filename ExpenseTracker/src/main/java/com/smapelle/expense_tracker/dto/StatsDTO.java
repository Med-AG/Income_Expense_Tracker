package com.smapelle.expense_tracker.dto;

import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {
	
	private double totalIncome;
	private double totalExpense;
	private Income lastIncome;
	private Expense lastExpense;
	private double balance;
	private double minIncome;
	private double maxIncome;
	private double minExpense;
	private double maxExpense;
	
}
