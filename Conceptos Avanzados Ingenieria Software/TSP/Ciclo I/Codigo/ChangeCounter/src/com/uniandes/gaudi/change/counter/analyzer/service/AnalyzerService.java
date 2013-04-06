package com.uniandes.gaudi.change.counter.analyzer.service;

import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;

/**
 * This interface exposes the logic for the analyzer module, there 
 * should be an instance for this class for every language supported
 * 
 * @class AnalyzerService.java
 * @author Felipe
 * @Date 6/04/2013
 * @since 1.0
 */
public interface AnalyzerService {

	/**
	 * This is the method in charge of perform the change analysis in every file
	 *  
	 * @param actualFileStructure with the actual file structure
	 * @param modifiedFileStructure with the modified file structure
	 * @return change structure with the modification information
	 * @throws AnalyzerServiceException
	 */
	public ChangeLOCStructure performAnalysis(LOCFileStructure actualFileStructure, LOCFileStructure modifiedFileStructure) throws AnalyzerServiceException;
}
