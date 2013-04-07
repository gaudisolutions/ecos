package com.uniandes.gaudi.change.counter.file.service;

import java.io.File;
import java.io.FilenameFilter;

/**
 * This class represents a file filter, that only the .java and .properties will
 * be used
 * 
 * @class FileFilter.java
 * @author Felipe
 * @Date 3/02/2013
 * @since 1.0
 */
public class FileFilter implements FilenameFilter {

	
	private String[] filterExtensions;
	
	/**
	 * Constructor with the file filter extensions, that will be read
	 *  
	 * @param filterExtensions
	 */
	public FileFilter(String... filterExtensions) {
		this.filterExtensions = filterExtensions;
	}
	
	/**
	 * This method filters the .java and .properties files
	 * 
	 * @param dir
	 *            is the dir where the file was found
	 * @param name
	 *            is the name of the file
	 */
	@Override
	public boolean accept(File dir, String name) {
		String filePath = dir.getAbsolutePath() + "/" + name;
		File file = new File(filePath);
		
		for (String fileExtension: filterExtensions) {
			if (name.endsWith(fileExtension) ||  file.isDirectory()) {
				return true;
			}
		}
		
		return false;
	}

}
