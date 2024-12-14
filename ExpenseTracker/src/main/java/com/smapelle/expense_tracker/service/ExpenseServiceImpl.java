package com.smapelle.expense_tracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smapelle.expense_tracker.dto.ExpenseDTO;
import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.exception.ExpenseNotFoundException;
import com.smapelle.expense_tracker.mappers.ExpenseMapperImpl;
import com.smapelle.expense_tracker.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ExpenseServiceImpl implements ExpenseService{
	
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ExpenseMapperImpl dtoMapper;
	
	
	@Override
	public ExpenseDTO postExpense(ExpenseDTO expenseDTO) {
		Expense expense = dtoMapper.fromExpenseDTO(expenseDTO);
		Expense savedExpense = expenseRepository.save(expense);
		return dtoMapper.fromExpense(savedExpense);
	}


	@Override
	public List<ExpenseDTO> getAllExpense() {
		return expenseRepository.findAll().stream()
				.sorted(Comparator.comparing(Expense::getDate).reversed())
				.map(t -> dtoMapper.fromExpense(t)).collect(Collectors.toList());
	}
	
	@Override
	public ExpenseDTO getExpenseById(Long id) throws ExpenseNotFoundException {
		 Expense expense = expenseRepository.findById(id).orElse(null);
		 if (expense == null) {
			throw new ExpenseNotFoundException("Expense is not present with id: "+id);
		}else {
			return dtoMapper.fromExpense(expense);
		}
	}
	
	@Override
	public ExpenseDTO updatExpense(long id, ExpenseDTO expenseDTO) throws ExpenseNotFoundException {
		expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense is not present with id: "+id));
		Expense expense = dtoMapper.fromExpenseDTO(expenseDTO);
		expense.setId(id);
		Expense savedExpense = expenseRepository.save(expense);
		return dtoMapper.fromExpense(savedExpense);
	}
	
	@Override
	public void deleteExpense(long id) throws ExpenseNotFoundException {
		Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense is not present with id: "+id));
		expenseRepository.deleteById(id);
	}

}
