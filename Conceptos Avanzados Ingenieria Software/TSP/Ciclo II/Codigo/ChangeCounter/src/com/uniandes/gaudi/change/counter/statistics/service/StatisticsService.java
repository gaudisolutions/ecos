package com.uniandes.gaudi.change.counter.statistics.service;

import com.uniandes.gaudi.change.counter.entity.ChangeStatistics;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.statistics.exception.StatisticsServiceException;

/**
 * @class StatisticsService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public interface StatisticsService {

	/**
	 * This method exposes the logic to generate the statistics 
	 * 
	 * @param projectPath
	 * @return
	 * @throws StatisticsServiceException
	 */
	void generateStatistics(LOCFileStructure modifiedFileStructure,
			String destinationPath) throws StatisticsServiceException;
}
