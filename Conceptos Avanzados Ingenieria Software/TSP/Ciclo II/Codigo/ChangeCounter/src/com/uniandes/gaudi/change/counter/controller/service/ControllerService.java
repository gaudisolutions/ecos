package com.uniandes.gaudi.change.counter.controller.service;

import com.uniandes.gaudi.change.counter.controller.exception.ControllerServiceException;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;

/**
 * This interface exposes the method to execute the change counter logic
 * 
 * @class ControllerService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public interface ControllerService {

	/**
	 * This method exposes the logic to compare to versions of a program
	 * 
	 * @param compareInfo with the user, change reason, modification type
	 * @throws ControllerServiceException when a controller service exception is thrown
	 */
	public void compareVersions(CompareInfo compareInfo) throws ControllerServiceException;
	
	/**
	 * This method exposes the logic to generate statistics
	 * 
	 * @param projectPath with the change label info
	 * @throws ControllerServiceException when a controller service exception is thrown 
	 */
	void generateStatistics(String projectPath, String destinationPath)
			throws ControllerServiceException;
	
}
