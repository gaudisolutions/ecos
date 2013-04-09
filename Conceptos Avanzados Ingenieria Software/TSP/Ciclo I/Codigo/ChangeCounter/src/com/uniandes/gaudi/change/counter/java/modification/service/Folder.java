package com.uniandes.gaudi.change.counter.java.modification.service;

/**
 * This class represents a folder
 * @class Folder.java
 * @author maria
 * @Date 8/04/2013
 * @since 1.0
 */
public class Folder {

	private String name;

	/**
	 * Higest level is 0
	 */
	private int level;
	
	/**
	 * Constructor with params
	 * @param name
	 * @param level
	 */
	public Folder(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}

	/**
	 * Returns the folder name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the folder name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the folder level
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the folder level
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public boolean equals(Object arg0) {
		boolean result=false;
		if(arg0 instanceof Folder){
			Folder folder= (Folder) arg0;
			if(folder.getLevel()==this.getLevel() && folder.getName().equals(this.getName())){
				result=true;
			}
		}
		
		return result;
	}
}
