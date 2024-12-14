package com.smapelle.expense_tracker.dto;

import java.util.List;

import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.entity.Income;

import lombok.Data;

@Data
public class GraphDTO {
	
	private List<Expense> expenselList;
	
	private List<Income> incomeList;
}
