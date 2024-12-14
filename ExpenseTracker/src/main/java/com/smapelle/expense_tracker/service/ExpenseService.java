package com.smapelle.expense_tracker.service;

import java.util.List;

import com.smapelle.expense_tracker.dto.ExpenseDTO;
import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.exception.ExpenseNotFoundException;

public interface ExpenseService {

	ExpenseDTO postExpense(ExpenseDTO expenseDTO);
	
	List<ExpenseDTO> getAllExpense();

	ExpenseDTO getExpenseById(Long id) throws ExpenseNotFoundException;

	ExpenseDTO updatExpense(long id, ExpenseDTO expenseDTO) throws ExpenseNotFoundException;

	void deleteExpense(long id) throws ExpenseNotFoundException;

}
