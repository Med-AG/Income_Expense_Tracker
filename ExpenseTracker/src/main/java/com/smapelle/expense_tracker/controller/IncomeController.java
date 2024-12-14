package com.smapelle.expense_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.smapelle.expense_tracker.dto.IncomeDTO;
import com.smapelle.expense_tracker.exception.ExpenseNotFoundException;
import com.smapelle.expense_tracker.exception.IncomeNotFoundException;
import com.smapelle.expense_tracker.service.IncomeService;

@RestController
@RequestMapping("/api/income")
@CrossOrigin
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	@PostMapping
	public ResponseEntity<?> postIncome(@RequestBody IncomeDTO incomeDTO) {
		IncomeDTO createdIncome = incomeService.postIncome(incomeDTO);
		if (createdIncome != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllIncomes() {
		return ResponseEntity.ok(incomeService.getAllIncome());
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateIncome(@PathVariable long id, @RequestBody IncomeDTO incomeDTO) {
		try {
			return ResponseEntity.ok(incomeService.updateIncome(id, incomeDTO));
		} catch (IncomeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(incomeService.getIncomeById(id));
		} catch (IncomeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
		try {
			incomeService.deleteIncome(id);
			return ResponseEntity.ok(null);
		} catch (IncomeNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
