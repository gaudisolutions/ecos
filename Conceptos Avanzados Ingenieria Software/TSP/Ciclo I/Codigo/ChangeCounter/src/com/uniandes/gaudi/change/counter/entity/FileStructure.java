package com.uniandes.gaudi.change.counter.entity;

import java.util.Map;

/**
 * This class represents a file structure
 * 
 * @class LOCStructure.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class FileStructure {

	/**
	 * stores locs files, the first key represents the package, 
	 * the second key represents the class name 
	 */
	private Map<String, Map<String, LOCFile>> packageFiles;
	
	/**
	 * @return the packageFiles
	 */
	public Map<String, Map<String, LOCFile>> getPackageFiles() {
		return packageFiles;
	}
	/**
	 * @param packageFiles the packageFiles to set
	 */
	public void setPackageFiles(Map<String, Map<String, LOCFile>> packageFiles) {
		this.packageFiles = packageFiles;
	}
}
