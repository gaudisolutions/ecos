package com.uniandes.gaudi.change.counter.java.modification.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LineCode;
import com.uniandes.gaudi.change.counter.modification.exception.ModificationServiceException;
import com.uniandes.gaudi.change.counter.modification.service.ModificationService;

/**
 * 
 * @class ModificationClient.java
 * @author maria
 * @Date 7/04/2013
 * @since 1.0
 */
public class ModificationClient implements ModificationService {

	/**
	 * This method exposes the login to perform label registro with the modification labels
	 * 
	 * @param changeLOCStructure with the change loc info
	 * @throws ModificationServiceException when an error is thrown writting modifications
	 */
	@Override
	public void performLabelRegistry(ChangeLOCStructure changeLOCStructure)
			throws ModificationServiceException {
		
		handleProgramStructure(changeLOCStructure.getPackageFiles());
	}		
	
	/**
	 * 
	 * @param packageStructure
	 */
	public void handleProgramStructure(Map<String, Map<String, LOCFile>> packageStructure){
		
		if(packageStructure==null){
			throw new IllegalArgumentException("The package structure is null");
		}
		
		Set<String> mapPackages = packageStructure.keySet();
		
		String srcPackage=null;
		File modifiedFolder=null;
		
		for (String actualPackage : mapPackages) {
			if(actualPackage.endsWith("src")){
				srcPackage = actualPackage;
				modifiedFolder = new File(srcPackage+"\\modified");
				modifiedFolder.mkdir();
				continue;
			}else{
				
			}

		}
		
		
	} 

	/**
	 * 
	 * @param line
	 * @return
	 */
	public Map<String,String> file2Json(LineCode line){
		return null;
		
	}
	
	/**
	 * 
	 * @param location
	 * @param labelLine
	 */
	public void createFile(String location,Map<String,String> labelLine){
		
	}
	
	/**
	 * 
	 * @param parent
	 * @param child
	 */
	public void createFolderStructure(File parent,File child){
		
}
	
	
	

	
}
