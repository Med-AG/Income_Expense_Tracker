package com.smapelle.expense_tracker.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.smapelle.expense_tracker.dto.IncomeDTO;
import com.smapelle.expense_tracker.entity.Income;

@Service
public class IncomeMapperImpl {

	public IncomeDTO fromIncome(Income income) {
		IncomeDTO incomeDTO = new IncomeDTO();
		BeanUtils.copyProperties(income, incomeDTO);
		return incomeDTO;
	}
	
	public Income fromIncomeDTO(IncomeDTO incomeDTO) {
		Income income = new Income();
		BeanUtils.copyProperties(incomeDTO, income);
		return income;
	}
	
}
