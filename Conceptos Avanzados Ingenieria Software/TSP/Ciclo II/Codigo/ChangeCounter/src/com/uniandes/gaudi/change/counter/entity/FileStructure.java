package com.uniandes.gaudi.change.counter.entity;

import java.util.HashMap;
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
	 * This constructor create the instance of package files
	 */
	public FileStructure() {
		packageFiles = new HashMap<String, Map<String,LOCFile>>();
	}
	
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
	
	/**
	 * This method adds a loc file to the loc structure
	 * 	
	 * @param packagePath
	 * @param fileName
	 * @param locFile
	 */
	public void addLOCFile(String packagePath, String fileName, LOCFile locFile) {
		
		if (!packageFiles.containsKey(packagePath)) {
			packageFiles.put(packagePath, new HashMap<String, LOCFile>());
		}
		
		Map<String, LOCFile> files = packageFiles.get(packagePath);
		files.put(fileName, locFile);
	}
}
