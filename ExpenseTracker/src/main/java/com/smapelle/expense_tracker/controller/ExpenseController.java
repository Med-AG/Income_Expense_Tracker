package com.smapelle.expense_tracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smapelle.expense_tracker.dto.ExpenseDTO;
import com.smapelle.expense_tracker.entity.Expense;
import com.smapelle.expense_tracker.exception.ExpenseNotFoundException;
import com.smapelle.expense_tracker.service.ExpenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin
public class ExpenseController {

	private final ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto) {
		ExpenseDTO createdExpense = expenseService.postExpense(dto);
		if (createdExpense != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllExpense() {
		return ResponseEntity.ok(expenseService.getAllExpense());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getExpenseById(@PathVariable long id) throws ExpenseNotFoundException {
		try {
			return ResponseEntity.ok(expenseService.getExpenseById(id));
		} catch (ExpenseNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateExpense(@PathVariable long id, @RequestBody ExpenseDTO expenseDTO) {
		try {
			return ResponseEntity.ok(expenseService.updatExpense(id, expenseDTO));
		} catch (ExpenseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable long id) {
		try {
			expenseService.deleteExpense(id);
			return ResponseEntity.ok(null);
		} catch (ExpenseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
