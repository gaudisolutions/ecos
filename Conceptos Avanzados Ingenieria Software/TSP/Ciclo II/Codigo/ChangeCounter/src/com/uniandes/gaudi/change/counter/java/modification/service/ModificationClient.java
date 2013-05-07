package com.uniandes.gaudi.change.counter.java.modification.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.sound.sampled.Line;

import org.json.JSONException;
import org.json.JSONObject;

import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.entity.BlockLOC;
import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.ChangeType;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.entity.LineCode;
import com.uniandes.gaudi.change.counter.modification.exception.ModificationServiceException;
import com.uniandes.gaudi.change.counter.modification.service.ModificationService;
import com.uniandes.gaudi.change.counter.util.file.FileUtil;

/**
 * 
 * @class ModificationClient.java
 * @author maria
 * @Date 7/04/2013
 * @since 1.0
 */
public class ModificationClient implements ModificationService {
	
	private Map<String,List<LineCode>>totalLines;
	

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
		Map<String,String> lineInfo = new LinkedHashMap<String, String>();
		Set<String> keys = totalLines.keySet();
		for (String path : keys) {
			List<LineCode> lines= totalLines.get(path);
			for (LineCode lineCode : lines) {
				lineInfo = line2Json(lineCode,changeLOCStructure.getVersion());
				createFile(path, lineInfo);
			}
		}
		
	}		
	
	/**
	 * This method is responsible of recover the data structure that represents the Program changes
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
		totalLines = new Hashtable<String, List<LineCode>>();
		
		
		for (String actualPackage : mapPackages) {
			
			String separator = "";
			
			//First the key must be split by folders
			if (File.separator.equals("\\")) {
				separator = "\\\\";
			} else {
				separator = File.separator;
			}
			
			String auxiliar[]=actualPackage.split(separator);
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
				
				List<LineCode> auxLines= new ArrayList<LineCode>();
				for (LineCode lineCode : partialLines) {
					List<LineCode> lines = getLineTree(lineCode);
					auxLines.addAll(lines);
				}
				String path = modifiedFolder.getAbsolutePath()+File.separator+actualPackage+File.separator+file+".modified";
				totalLines.put(path,auxLines);
			}
			
		}
		
		StringBuilder path = new StringBuilder(srcPackage+File.separator+"modified");
		
		for (Folder folder : sourcePackages) {
			path.append(File.separator).append(folder.getName());
		}
		createFolderStructure(new File(path.toString()));
		
		
		
	} 

	/**
	 * This method takes a LineCode object and turn it in a JSON structure
	 * @param line
	 * @param version 
	 * @return lineInfo
	 * @throws ModificationServiceException 
	 */
	public Map<String,String> line2Json(LineCode line, double version) throws ModificationServiceException{
		
		Map<String,String> lineInfo = new Hashtable<String, String>();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(line.getChangeType().name(), line.getLineNumber());
			if(line.getChangeType().equals(ChangeType.KEPT)){
				jsonObject.put("VERSION", version-1);
			}
			else{
				jsonObject.put("VERSION", version);
			}
			
			lineInfo.put(jsonObject.toString(), line.getLine());
		} catch (JSONException e) {
			throw new ModificationServiceException(e.getMessage(),e);
		}
		return lineInfo;
	}
	
	/**
	 * This method create the new files with the change labels
	 * @param location
	 * @param labelLine
	 * @throws ModificationServiceException 
	 */
	public void createFile(String location,Map<String,String> labelLine) throws ModificationServiceException{
		File newFile = new File(location);
		FileWriter writer = null;
		try {
			FileUtil.getInstance().createParentDirectories(newFile);
			
			newFile.createNewFile();
			writer = new FileWriter(newFile, true);
			StringBuilder fileContent = new StringBuilder();
			
			Set<String> labels = labelLine.keySet();
			for (String label : labels) {
				fileContent.append(label+"\t"+labelLine.get(label)+"\r\n");
			}
			
			writer.append(fileContent.toString());
			writer.flush();
			
		} catch (IOException e) {
			throw new ModificationServiceException(e.getMessage(),e);
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
		
	}

	/**
	 * Creates a folder structure for modified files
	 * @param parent
	 */
	public void createFolderStructure(File path){
		if(path!=null){
			path.mkdir();
		}else
			throw new IllegalArgumentException("The package under src is null");
		}
		

	/**
	 * This method is recursive and its function is go through the whole line structure of a File
	 * @param currentLine
	 * @return totalLines
	 */
	public List<LineCode> getLineTree(LineCode currentLine){
		List<LineCode> totalLines = new ArrayList<LineCode>();
		
		totalLines.add(currentLine);
		if(currentLine instanceof BlockLOC){
			List<LineCode> currentLines=  ((BlockLOC) currentLine).getLocs();
			for (LineCode lineCode : currentLines) {
				totalLines.addAll(getLineTree(lineCode));
			}
		}
		
		return totalLines;
	}

	/**
	 * This method exposes the logic to perform the header with the modification
	 * 
	 * @param changeLOCStructure with the change loc info
	 * @param modifiedFileStructure with the modified file structure
	 * @throws ModificationServiceException when an error is thrown writting modifications
	 */
	public void performHeader(LOCFileStructure modifiedFileStructure,
			ChangeLOCStructure changeLOCStructure)throws ModificationServiceException {
		

		
		

		
			
		Map<String, Map<String, LOCFile>> modifiedPackageFiles = modifiedFileStructure.getPackageFiles();
		
		Set<String> modifiedPackages = modifiedPackageFiles.keySet();
		Set<String> allPackages = new HashSet<String>(); 
		
		allPackages.addAll(modifiedPackages);
		try {
			
			for (String packageName : allPackages) {
				Map<String, LOCFile> changedLOCFiles;
				

					changedLOCFiles = new HashMap<String, LOCFile>();
					
					Map<String, LOCFile> modifiedLOCFiles = modifiedPackageFiles.get(packageName);
					
					Set<String> modifiedClases = modifiedLOCFiles.keySet();
					Set<String> allClasses = new HashSet<String>(); 
					
					allClasses.addAll(modifiedClases);
					
					for (String className : allClasses) {					
						
						
						if (modifiedLOCFiles.get(className) != null ) {
							
							searchMain(modifiedLOCFiles.get(className));
							
							
							if(modifiedLOCFiles.get(className).isMain()){
								
								
								
							modifyHeader(modifiedLOCFiles.get(className),changeLOCStructure);
							
							}
						}

					}
					

				}
				

				

					
		} catch (Exception e) {
			throw new ModificationServiceException("Excepción al modificar la clase" + modifiedFileStructure.getPath(), e.getCause());
		}
		
		

		
	}

	private void modifyHeader(LOCFile locFile, ChangeLOCStructure changeLOCStructure) throws ModificationServiceException {
		
		StringBuilder fileContent = new StringBuilder();
		fileContent.append(searchPreviousHeader(locFile));
		reWriteHeader(fileContent,changeLOCStructure,locFile.getPath());
		
	}

	private void reWriteHeader(StringBuilder fileContent,
			ChangeLOCStructure changeLOCStructure, String path) throws ModificationServiceException {
		BufferedWriter buff = null;
		StringBuilder newHeader=new StringBuilder();
		newHeader.append("/*"+"\r\n");
		newHeader.append("*<ChangeCounter>"+"\r\n");
		newHeader.append("*<VERSION> "+changeLOCStructure.getVersion()+"\r\n");
		newHeader.append("*<AUTHOR> "+changeLOCStructure.getCompareInfo().getModifiedBy()+"\r\n");
		Date fecha = new Date();
		newHeader.append("*<CHANGES_DATE> "+ fecha+"\r\n");
		newHeader.append("*<CHANGES_REASON> "+ changeLOCStructure.getCompareInfo().getChangeReason()+"\r\n");
		newHeader.append("*<TOTAL_LOCS> "+ changeLOCStructure.getTotal()+"\r\n");
		newHeader.append("*<TOTAL_LOCS_ADDED> "+ changeLOCStructure.getTotalAdded()+"\r\n");
		newHeader.append("*<TOTAL_LOCS_DELETED> "+ changeLOCStructure.getTotalDeleted()+"\r\n");
		newHeader.append("*/"+"\r\n");
		
		try {
			
			buff=new BufferedWriter(new FileWriter(path));
			
			buff.append(newHeader.toString());
	
			
			buff.append(fileContent.toString());
			buff.flush();
			
		} catch (IOException e) {
			throw new ModificationServiceException(e.getMessage(),e);
		}finally{
			try {
				buff.close();
			} catch (IOException e) {
			}
		}
		
	}

	private StringBuilder searchPreviousHeader(LOCFile locFile) {
		StringBuilder fileLines=new StringBuilder();
		String line="";
		
		try {
			BufferedReader buff=new BufferedReader(new FileReader(locFile.getPath()));
			
			while((line=buff.readLine())!=null){
							
				
				line=Pattern.compile(String.format("\\<+ChangeCounter+\\>")).matcher(line).replaceAll("<PreviousChangeCounter>");
										
				line=Pattern.compile(String.format("\\<+VERSION+\\>")).matcher(line).replaceAll("<PreviousVERSION>");
				
				line=Pattern.compile(String.format("\\<+AUTHOR+\\>")).matcher(line).replaceAll("<PreviousAUTHOR>");
				
				line=Pattern.compile(String.format("\\<+CHANGES_DATE+\\>")).matcher(line).replaceAll("<PreviousCHANGES_DATE>");				
				
				line=Pattern.compile(String.format("\\<+CHANGES_REASON+\\>")).matcher(line).replaceAll("<PreviousCHANGES_REASON>");
				
				line=Pattern.compile(String.format("\\<+TOTAL_LOCS+\\>")).matcher(line).replaceAll("<PreviousTOTAL_LOCS>");
				
				line=Pattern.compile(String.format("\\<+TOTAL_LOCS_ADDED+\\>")).matcher(line).replaceAll("<PreviousTOTAL_LOCS_ADDED>");
				
				line=Pattern.compile(String.format("\\<+TOTAL_LOCS_DELETED+\\>")).matcher(line).replaceAll("<PreviousTOTAL_LOCS_DELETED>");
					
				fileLines.append(line+"\r\n");

				
			}
			buff.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return fileLines;
	}

	private void searchMain(LOCFile modifiedLOCFile) throws ModificationServiceException {
		List<LineCode> locsModifiedFile = modifiedLOCFile.getLocs();

		try {
			

			
			for (int i = 0; i < locsModifiedFile.size(); i++) {
			
				LineCode line = new LineCode();
				if (locsModifiedFile.get(i)!=null) {
				if (locsModifiedFile.get(i).isMain()){
					
					modifiedLOCFile.setMain(true);
					
				}
				}
				
			}
			
			
		} catch (Exception e) {
			throw new ModificationServiceException("Excepción al buscar la clase que contiene el main" + modifiedLOCFile.getName(), e.getCause());
		}
				

		
	
		
	}
	

	
}
