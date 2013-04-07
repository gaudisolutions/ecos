package com.uniandes.gaudi.change.counter.analyzer.service.java;

import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;

/**
 * This class represents the implementation of Analyzer module
 * for Java Language 
 * @class JavaAnalyzerService.java
 * @author Julián
 * @Date 07/04/2013
 * @since 1.0
 */
public class JavaAnalyzerService implements AnalyzerService{

	
	@Override
	public ChangeLOCStructure performAnalysis(LOCFileStructure actualFileStructure, LOCFileStructure modifiedFileStructure) throws AnalyzerServiceException {
		
		return null;
	}
	
	/**
	 * This is the method in charge of calculate total lines for the new program version and update the change structure  
	 * @param change structure with total lines including added
	 * and deleted lines in the new program version 
	 * @throws AnalyzerServiceException
	 */
	public void countLines(ChangeLOCStructure changeLOCStructure) throws AnalyzerServiceException {
		
	}
	
	/**
	 * This method creates a new instance of LOCFile by identifying total class lines and which ones were added, deleted and kept
	 * @param Modified program LOCFile instance
	 * @param Actual program LOCFile instance 
	 * @return LOCFile instance that contains differences between modified and actual LOCFile
	 */
	private LOCFile compareLOCFiles(LOCFile modifiedLOCFile, LOCFile actualLOCFile) {
		return null;
	}
	
	/**
	 * This method is responsible for updating LOCFile instance to mark all lines as added
	 * @param New LOCFile
	 */
	private void addNewLOCFile(LOCFile locFile) {
		
	}
	
	/**
	 * This method is responsible for updating LOCFile instance to mark all lines as deleted
	 * @param Deleted LOCFile
	 */
	private void addDeletedLOCFile(LOCFile locFile) {
		
	}
}
