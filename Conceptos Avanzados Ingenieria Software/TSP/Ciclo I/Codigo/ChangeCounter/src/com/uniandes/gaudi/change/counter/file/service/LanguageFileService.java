package com.uniandes.gaudi.change.counter.file.service;

import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;

/**
 * This class implements the file service for every language
 * 
 * @class LanguageFileService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class LanguageFileService implements FileService {

	/**
	 * Stores a parser for the source file in a specific language
	 */
	private LOCFileParser locFileParser;
	/**
	 * Stores a file filter to loop the source code, for a specific language
	 */
	private FileFilter fileFilter;
	
	/**
	 * Constructor for the language file service that receives a loc file parser instance
	 * 
	 * @param locFileParser instance to parse the code files into object structure
	 */
	public LanguageFileService(LOCFileParser locFileParser, FileFilter fileFilter) {
		super();
		this.locFileParser = locFileParser;
		this.fileFilter = fileFilter;
	}

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

	/**
	 * This is a recursive method where all the files in the source path are
	 * searched and parsed to classFile objects
	 * 
	 * if the file is a folder, loops through the folder searching files
	 * if the file is a file in the system, with the accepted extension, it is parsed 
	 * to a file structure  
	 * 
	 * @param rootFile represents the root file to be parsed
	 */
	private void buildLOCFileStructure() {
		
	}
}
