package com.uniandes.gaudi.change.counter.entity;


/**
 * This class stores a file structure for the source code 
 * 
 * @class LOCFileStructure.java
 * @author Felipe
 * @Date 6/04/2013
 * @since 1.0
 */
public class LOCFileStructure extends FileStructure{

	/**
	 * source path for the given project
	 */
	private String path;
	/**
	 * This represents the project, actual or modified
	 */
	private ProjectType type;
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the type
	 */
	public ProjectType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(ProjectType type) {
		this.type = type;
	}
		
}
