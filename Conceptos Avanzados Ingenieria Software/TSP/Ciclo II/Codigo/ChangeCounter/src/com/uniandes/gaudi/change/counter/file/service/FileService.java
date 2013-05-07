package com.uniandes.gaudi.change.counter.file.service;

import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;

/**
 * This interface exposes the logic to get the info from the file classes
 * 
 * @class FileService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public interface FileService {

	/**
	 * This method exposes the logic to read the file and generate a loc file structure
	 *  
	 * @param projectPath for the given project 
	 * @return LOCFileStructure with the files and loc info
	 * @throws FileServiceException when an error reading the file is thrown
	 */
	public LOCFileStructure readFile(String projectPath) throws FileServiceException;

	/**
	 * This method exposes the logic to read the file and generate a loc file structure
	 *  
	 * @param projectPath for the given project 
	 * @return LOCFileStructure with the files and loc info
	 * @throws FileServiceException when an error reading the file is thrown
	 */
	public LOCFileStructure readModifiedFile(String projectPath)
			throws FileServiceException;
}
