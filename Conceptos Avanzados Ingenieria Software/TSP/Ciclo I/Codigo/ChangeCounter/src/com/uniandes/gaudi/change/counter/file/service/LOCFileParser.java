package com.uniandes.gaudi.change.counter.file.service;

import java.io.File;

import com.uniandes.gaudi.change.counter.entity.LOCFile;

/**
 * This interface exposes the logic to parse a file in a given language
 * 
 * @class LOCFileParser.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public interface LOCFileParser {

	/**
	 * Method to parse a file in a given language into a LOCFile
	 *  
	 * @param file path for the source file
	 * @return LOCFile with the info 
	 */
	public LOCFile parseFile(File file);
}
