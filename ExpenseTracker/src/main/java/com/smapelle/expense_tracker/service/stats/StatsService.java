package com.smapelle.expense_tracker.service.stats;

import com.smapelle.expense_tracker.dto.GraphDTO;
import com.smapelle.expense_tracker.dto.StatsDTO;

public interface StatsService {

	GraphDTO getChartData();
	
	StatsDTO getStats();
	
}
