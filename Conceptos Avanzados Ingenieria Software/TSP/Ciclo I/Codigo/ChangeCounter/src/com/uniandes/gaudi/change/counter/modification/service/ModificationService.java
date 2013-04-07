package com.uniandes.gaudi.change.counter.modification.service;

import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.modification.exception.ModificationServiceException;

/**
 * This interface exposes the logic for the file modification service
 * 
 * @class ModificationService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public interface ModificationService {

	/**
	 * This method exposes the login to perform label registro with the modification labels
	 * 
	 * @param changeLOCStructure with the change loc info
	 * @throws ModificationServiceException when an error is thrown writting modifications
	 */
	public void performLabelRegistry(ChangeLOCStructure changeLOCStructure) throws ModificationServiceException;
}
