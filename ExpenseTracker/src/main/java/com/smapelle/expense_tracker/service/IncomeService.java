package com.smapelle.expense_tracker.service;

import java.util.List;

import com.smapelle.expense_tracker.dto.IncomeDTO;
import com.smapelle.expense_tracker.exception.IncomeNotFoundException;

public interface IncomeService {

	IncomeDTO postIncome(IncomeDTO incomeDTO);

	List<IncomeDTO> getAllIncome();

	IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) throws IncomeNotFoundException;

	IncomeDTO getIncomeById(Long id) throws IncomeNotFoundException;

	void deleteIncome(Long id) throws IncomeNotFoundException;

}
