package com.uniandes.gaudi.change.counter.java.modification.service;

import java.util.Comparator;

/**
 * This class allows perform sort tasks (by level) over a TreeSet that stores Folder objects
 * @class FolderLevelComparator.java
 * @author maria
 * @Date 8/04/2013
 * @since 1.0
 */
public class FolderLevelComparator implements Comparator<Folder> {

	@Override
	public int compare(Folder folder0, Folder folder1) {
		// TODO Auto-generated method stub
		return folder0.getLevel()-folder1.getLevel() ;
	}

}
