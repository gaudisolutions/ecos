package com.uniandes.gaudi.change.counter.java.modification.service;

import java.util.Comparator;

public class FolderLevelComparator implements Comparator<Folder> {

	@Override
	public int compare(Folder folder0, Folder folder1) {
		// TODO Auto-generated method stub
		return folder0.getLevel()-folder1.getLevel() ;
	}

}
