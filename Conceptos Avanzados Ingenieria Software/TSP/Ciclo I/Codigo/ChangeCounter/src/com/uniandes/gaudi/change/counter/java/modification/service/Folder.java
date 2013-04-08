package com.uniandes.gaudi.change.counter.java.modification.service;

public class Folder {

	private String name;

	/**
	 * Higest level is 0
	 */
	private int level;
	
	public Folder(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

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
