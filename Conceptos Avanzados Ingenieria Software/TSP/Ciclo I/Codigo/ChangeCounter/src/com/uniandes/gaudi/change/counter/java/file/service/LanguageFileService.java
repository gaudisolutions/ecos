package com.uniandes.gaudi.change.counter.java.file.service;

import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;
import com.uniandes.gaudi.change.counter.file.service.FileService;

/**
 * @class LanguageFileService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class LanguageFileService implements FileService {

	/**
	 * This method exposes the logic to read the file and generate a loc file structure
	 *  
	 * @param projectPath for the given project 
	 * @return LOCFileStructure with the files and loc info
	 * @throws FileServiceException when an error reading the file is thrown
	 */
	@Override
	public LOCFileStructure readFile(String projectPath)
			throws FileServiceException {
		
		return null;
	}

}
