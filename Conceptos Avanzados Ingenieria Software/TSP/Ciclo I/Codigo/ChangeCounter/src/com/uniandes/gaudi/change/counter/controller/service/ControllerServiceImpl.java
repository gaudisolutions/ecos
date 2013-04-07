package com.uniandes.gaudi.change.counter.controller.service;

import com.uniandes.gaudi.change.counter.controller.exception.ControllerServiceException;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;

/**
 * This class is in charge to implement the logic for the contollerService,
 * and is in charge to orchestrate invocations to other modules  
 * 
 * @class ControllerServiceImpo.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class ControllerServiceImpl implements ControllerService {

	/**
	 * This method exposes the logic to compare to versions of a program
	 * 1. calls the FileService to read the files and create a object model
	 * structure
	 * 2. calls the AnalyzerService to analyze changes between files
	 * 3. calls the ModificationService to store file labels with modification
	 * information.
	 * 
	 * All the instance for the modules are obtain using the service factory
	 * to get specific instances for every language supported
	 * 
	 * @param compareInfo with the user, change reason, modification type
	 * @throws ControllerServiceException when a controller service exception is thrown
	 */
	@Override
	public void compareVersions(CompareInfo compareInfo)
			throws ControllerServiceException {

	}

	/**
	 * This method exposes the logic to generate statistics
	 * 
	 * @param projectPath with the change label info
	 * @throws ControllerServiceException when a controller service exception is thrown 
	 */
	@Override
	public void generateStatistics(String projectPath)
			throws ControllerServiceException {

	}

}
