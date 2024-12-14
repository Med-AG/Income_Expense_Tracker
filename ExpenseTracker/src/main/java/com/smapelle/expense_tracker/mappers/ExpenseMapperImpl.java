package com.smapelle.expense_tracker.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.smapelle.expense_tracker.dto.ExpenseDTO;
import com.smapelle.expense_tracker.entity.Expense;

@Service
public class ExpenseMapperImpl {

	public ExpenseDTO fromExpense(Expense expense) {
		ExpenseDTO expenseDTO = new ExpenseDTO();
		BeanUtils.copyProperties(expense, expenseDTO);
		return expenseDTO;
	}
	
	public Expense fromExpenseDTO(ExpenseDTO expenseDTO) {
		Expense expense = new Expense();
		BeanUtils.copyProperties(expenseDTO, expense);
		return expense;
	}
	
	
}
