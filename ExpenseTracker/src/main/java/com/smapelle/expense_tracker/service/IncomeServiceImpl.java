package com.smapelle.expense_tracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smapelle.expense_tracker.dto.IncomeDTO;
import com.smapelle.expense_tracker.entity.Income;
import com.smapelle.expense_tracker.exception.ExpenseNotFoundException;
import com.smapelle.expense_tracker.exception.IncomeNotFoundException;
import com.smapelle.expense_tracker.mappers.IncomeMapperImpl;
import com.smapelle.expense_tracker.repository.IncomeRepository;


@Service
public class IncomeServiceImpl implements IncomeService{
	
	private IncomeRepository incomeRepository;
	private IncomeMapperImpl dtoMapper;
	
	public IncomeServiceImpl(IncomeRepository incomeRepository, IncomeMapperImpl mapperImpl) {
		this.incomeRepository = incomeRepository;
		this.dtoMapper = mapperImpl;
	}
	
	@Override
	public IncomeDTO postIncome(IncomeDTO incomeDTO) {
		Income income = dtoMapper.fromIncomeDTO(incomeDTO);
		Income savedIncome = incomeRepository.save(income);
		return dtoMapper.fromIncome(savedIncome);
	}
	
	@Override
	public List<IncomeDTO> getAllIncome(){
		 return incomeRepository.findAll().stream()
				 .sorted(Comparator.comparing(Income::getDate).reversed())
				 .map(t -> dtoMapper.fromIncome(t)).collect(Collectors.toList());
		
	}
	
	@Override
	public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) throws IncomeNotFoundException {
		Income income = dtoMapper.fromIncomeDTO(getIncomeById(id));
		incomeDTO.setId(id);
		return postIncome(incomeDTO);
		
		// other way to do
		//income = dtoMapper.fromIncomeDTO(incomeDTO);
		//income.setId(id);
		//return dtoMapper.fromIncome(income);
		
		/*
		 *Second Way To Do ------------------------
		income.setAmount(incomeDTO.getAmount());
		income.setCategory(incomeDTO.getCategory());
		income.setDate(incomeDTO.getDate());
		income.setDescription(incomeDTO.getDescription());
		income.setTitle(incomeDTO.getTitle());
		return postIncome(dtoMapper.fromIncome(income));
		*/
		
	}
	
	@Override
	public IncomeDTO getIncomeById(Long id) throws IncomeNotFoundException {
		Income income = incomeRepository.findById(id).orElseThrow(() -> new IncomeNotFoundException("Income is not present with id: "+id));
		return dtoMapper.fromIncome(income);
	}
	
	@Override
	public void deleteIncome(Long id) throws IncomeNotFoundException {
		Income income = dtoMapper.fromIncomeDTO(getIncomeById(id));
		incomeRepository.delete(income);
	}
	
}
