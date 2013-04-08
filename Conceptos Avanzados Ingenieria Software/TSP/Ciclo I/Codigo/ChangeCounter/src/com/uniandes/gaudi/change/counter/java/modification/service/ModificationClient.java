package com.uniandes.gaudi.change.counter.java.modification.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sound.sampled.Line;

import com.uniandes.gaudi.change.counter.entity.BlockLOC;
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
	
	private List<LineCode> totalLines;

	/**
	 * This method exposes the login to perform label registro with the modification labels
	 * 
	 * @param changeLOCStructure with the change loc info
	 * @throws ModificationServiceException when an error is thrown writting modifications
	 */
	@Override
	public void performLabelRegistry(ChangeLOCStructure changeLOCStructure)
			throws ModificationServiceException {
		
		handleProgramStructure(changeLOCStructure.getPackageFiles(),changeLOCStructure.getCompareInfo().getModifiedPath());
		for (LineCode  line: totalLines) {
			line2Json(line);
		}
	}		
	
	/**
	 * 
	 * @param packageStructure
	 */
	public void handleProgramStructure(Map<String, Map<String, LOCFile>> packageStructure, String modifiedPath){
		
		if(packageStructure==null){
			throw new IllegalArgumentException("The package structure is null");
		}
		
		String srcPackage=modifiedPath;
		File modifiedFolder = new File(srcPackage+File.separator+"modified");
		modifiedFolder.mkdir();

		Set<String> mapPackages = packageStructure.keySet();
		Set<Folder> sourcePackages = new TreeSet<Folder>(new FolderLevelComparator());
		
		
		for (String actualPackage : mapPackages) {
			//First the key must be split by folders
			String auxiliar[]=actualPackage.split(File.separator);
			//Next the paths will be turn into Folder objects and store in a TreeSet sorted by level
			for (int i = 0; i < auxiliar.length; i++) {
				Folder actualFolder = new Folder(auxiliar[i],i);
				sourcePackages.add(actualFolder);
			}
			//Now is time to get the values(Files or Classes)
			
			Map<String,LOCFile> fileMap =packageStructure.get(actualPackage);
			
			Set<String> files = fileMap.keySet();
			for (String file : files) {
				LOCFile actualLocFile= fileMap.get(file);
				List<LineCode> partialLines = actualLocFile.getLocs();
				//Get line file´s line tree
				totalLines = new ArrayList<LineCode>();
				for (LineCode lineCode : partialLines) {
					List<LineCode> lines = getLineTree(lineCode);
					totalLines.addAll(lines);
				}
				
			}
			
		}
		
		StringBuilder path = new StringBuilder(srcPackage+File.separator+"modified");
		
		for (Folder folder : sourcePackages) {
			path.append(File.separator).append(folder.getName());
		}
		createFolderStructure(new File(path.toString()));
		
		
		
	} 

	/**
	 * 
	 * @param line
	 * @return
	 */
	public Map<String,String> line2Json(LineCode line){
		Map<String,String> lineInfo = new Hashtable<String, String>();
		
		return lineInfo;
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
	 */
	public void createFolderStructure(File path){
		if(path!=null){
			path.mkdir();
		}else
			throw new IllegalArgumentException("The package under src is null");
		}
		

	
	public List<LineCode> getLineTree(LineCode currentLine){
		List<LineCode> totalLines = new ArrayList<LineCode>();
		
		totalLines.add(currentLine);
		if(currentLine instanceof BlockLOC){
			List<LineCode> currentLines=  ((BlockLOC) currentLine).getLocs();
			for (LineCode lineCode : currentLines) {
				getLineTree(lineCode);
			}
		}
		
		return totalLines;
	}
	

	
}
