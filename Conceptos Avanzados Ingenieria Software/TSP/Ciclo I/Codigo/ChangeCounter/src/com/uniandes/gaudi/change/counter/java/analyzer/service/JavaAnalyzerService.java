package com.uniandes.gaudi.change.counter.java.analyzer.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.ChangeType;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.entity.LineCode;

/**
 * This class represents the implementation of Analyzer module
 * for Java Language 
 * @class JavaAnalyzerService.java
 * @author Julián
 * @Date 07/04/2013
 * @since 1.0
 */
public class JavaAnalyzerService implements AnalyzerService{

	/**
	 * This method performs the change analysis in every file
	 *  
	 * @param actualFileStructure with the actual file structure
	 * @param modifiedFileStructure with the modified file structure
	 * @return change structure with the modification information
	 * @throws AnalyzerServiceException
	 */
	@Override
	public ChangeLOCStructure performAnalysis(LOCFileStructure actualFileStructure, LOCFileStructure modifiedFileStructure) throws AnalyzerServiceException {
		
		if (actualFileStructure == null && modifiedFileStructure == null) {
			throw new AnalyzerServiceException("Las estructuras de archivos a comparar no pueden ser nulas");
		}
		
		ChangeLOCStructure changeLOCStructure = new ChangeLOCStructure();
		changeLOCStructure = new ChangeLOCStructure();
		changeLOCStructure.setCompareInfo(new CompareInfo());
		changeLOCStructure.getCompareInfo().setActualPath(actualFileStructure.getPath());
		changeLOCStructure.getCompareInfo().setModifiedPath(modifiedFileStructure.getPath());
		
		Map<String, Map<String, LOCFile>> changedPackageFiles = new HashMap<String, Map<String,LOCFile>>();		
		Map<String, Map<String, LOCFile>> modifiedPackageFiles = modifiedFileStructure.getPackageFiles();
		Map<String, Map<String, LOCFile>> actualPackageFiles = actualFileStructure.getPackageFiles();
		
		Set<String> modifiedPackages = modifiedPackageFiles.keySet();
		Set<String> actualPackages = actualPackageFiles.keySet();
		Set<String> allPackages = new HashSet<String>(); 
		
		allPackages.addAll(modifiedPackages);
		allPackages.addAll(actualPackages);
		try {
			
			for (String packageName : allPackages) {
				Map<String, LOCFile> changedLOCFiles;
				
				/* If the package exists in both structures (Actual and Modified) */
				if (modifiedPackageFiles.get(packageName) != null && actualPackageFiles.get(packageName) != null) {
					changedLOCFiles = new HashMap<String, LOCFile>();
					
					Map<String, LOCFile> actualLOCFiles = actualPackageFiles.get(packageName);
					Map<String, LOCFile> modifiedLOCFiles = modifiedPackageFiles.get(packageName);
					
					Set<String> modifiedClases = modifiedLOCFiles.keySet();
					Set<String> actualClases = actualLOCFiles.keySet();
					Set<String> allClasses = new HashSet<String>(); 
					
					allClasses.addAll(modifiedClases);
					allClasses.addAll(actualClases);
					
					for (String className : allClasses) {
						
						/* If the class exists in both packages (Actual and Modified) */
						if (modifiedLOCFiles.get(className) != null && actualLOCFiles.get(className) != null) {
							LOCFile updatedLOCFile = compareLOCFiles(modifiedLOCFiles.get(className), actualLOCFiles.get(className));
							changedLOCFiles.put(className, updatedLOCFile);					
						}
						
						/* If the class was deleted from previous(Actual) program version -> All DELETED */
						if (modifiedLOCFiles.get(className) == null) {
							LOCFile deletedLOCFile = (LOCFile) actualLOCFiles.get(className).clone();
							addDeletedLOCFile(deletedLOCFile);
							changedLOCFiles.put(className, deletedLOCFile);						
						}
						
						/* If the class was added in the actual(Modified) program version -> All ADDED */
						if (actualLOCFiles.get(className) == null) {
							LOCFile newLOCFile = (LOCFile) modifiedLOCFiles.get(className).clone();
							addNewLOCFile(newLOCFile);
							changedLOCFiles.put(className, newLOCFile);
						}
					}
					
					changedPackageFiles.put(packageName, changedLOCFiles);
					continue;
				}
				
				/* If the package in modifiedFileStructure was deleted -> All DELETED */
				if (modifiedPackageFiles.get(packageName) == null) {
					changedLOCFiles = new HashMap<String, LOCFile>();
					Map<String, LOCFile> locFilesPackage = actualPackageFiles.get(packageName);
					for (LOCFile locFile : locFilesPackage.values()) {
						LOCFile newLOCFile = (LOCFile) locFile.clone();
						addDeletedLOCFile(newLOCFile);
						changedLOCFiles.put(locFile.getName(), newLOCFile);
					}
					changedPackageFiles.put(packageName, changedLOCFiles);
				}
				
				/* If the package in modifiedFileStructure is new -> All ADDED */
				if (actualPackageFiles.get(packageName) == null) {
					changedLOCFiles = new HashMap<String, LOCFile>();
					Map<String, LOCFile> locFilesPackage = modifiedPackageFiles.get(packageName);
					for (LOCFile locFile : locFilesPackage.values()) {
						LOCFile newLOCFile = (LOCFile) locFile.clone();
						addNewLOCFile(newLOCFile);
						changedLOCFiles.put(locFile.getName(), newLOCFile);
					}
					changedPackageFiles.put(packageName, changedLOCFiles);
				}
			}		
		} catch (Exception e) {
			throw new AnalyzerServiceException("Excepción al comparar las clases del paquete" + modifiedFileStructure.getPath(), e.getCause());
		}
		
		changeLOCStructure.setPackageFiles(changedPackageFiles);		
		countLines(changeLOCStructure);		
		return changeLOCStructure;
		
	}
	
	/**
	 * This is the method in charge of calculate total lines for the new program version and update the change structure  
	 * @param change structure with total lines including added
	 * and deleted lines in the new program version 
	 * @throws AnalyzerServiceException
	 */
	public void countLines(ChangeLOCStructure changeLOCStructure) throws AnalyzerServiceException {
		
		if (changeLOCStructure == null || changeLOCStructure.getPackageFiles() == null) {
			throw new AnalyzerServiceException("La estructura de lineas modificadas no puede ser nula");
		}
		
		Collection<Map<String, LOCFile>> packageFiles = changeLOCStructure.getPackageFiles().values();
		int programLines = BigInteger.ZERO.intValue();
		int totalAdded = BigInteger.ZERO.intValue();
		int totalDeleted = BigInteger.ZERO.intValue();
		for (Map<String, LOCFile> locFilesPackage : packageFiles) {
			
			Collection<LOCFile> locFiles = locFilesPackage.values();		
			for (LOCFile locFile : locFiles) {
				programLines += locFile.getTotal();
				totalAdded += locFile.getTotalAdded();
				totalDeleted += locFile.getTotalDeleted();
			}
		}
		
		changeLOCStructure.setTotal(programLines);
		changeLOCStructure.setTotalAdded(totalAdded);
		changeLOCStructure.setTotalDeleted(totalDeleted);
	}
	
	/**
	 * This method creates a new instance of LOCFile by identifying total class lines and which ones were added, deleted and kept
	 * @param Modified program LOCFile instance
	 * @param Actual program LOCFile instance 
	 * @return LOCFile instance that contains differences between modified and actual LOCFile
	 * @throws AnalyzerServiceException
	 */
	private LOCFile compareLOCFiles(LOCFile modifiedLOCFile, LOCFile actualLOCFile) throws AnalyzerServiceException {
		List<LineCode> locsModifiedFile = modifiedLOCFile.getLocs();
		List<LineCode> locsActualFile = actualLOCFile.getLocs();		
		List<LineCode> locs = new ArrayList<LineCode>();
		int lineNumber = 0;
		int totalAdded = 0;
		int totalDeleted = 0;
		try {
			
			/* Fills the smaller list with null values to avoid exception at iteration */ 
			if (locsActualFile.size() != locsModifiedFile.size()) {	
				while (locsModifiedFile.size()>locsActualFile.size()) {
					locsActualFile.add(null);
				}
				while (locsModifiedFile.size()<locsActualFile.size()) {
					locsModifiedFile.add(null);					
				}				
			}
			
			for (int i = 0; i < locsModifiedFile.size(); i++) {
				LineCode line = new LineCode();
				if (locsModifiedFile.get(i)==null) {
					line = (LineCode) locsActualFile.get(i).clone();
					line.setLineNumber(lineNumber);
					line.setChangeType(ChangeType.DELETED);
					locs.add(line);
					lineNumber++;
					totalDeleted++;
					continue;
				} 
				
				if (locsActualFile.get(i)==null) {
					line = (LineCode) locsModifiedFile.get(i).clone();
					line.setLineNumber(lineNumber);
					line.setChangeType(ChangeType.ADDED);
					locs.add(line);
					lineNumber++;
					totalAdded++;
					continue;
				}
								
				line = (LineCode) locsModifiedFile.get(i).clone();
				line.setLineNumber(lineNumber);				
				if (locsModifiedFile.get(i).getLine().equals(locsActualFile.get(i).getLine())) {
					line.setChangeType(ChangeType.KEPT);					
					locs.add(line);
					lineNumber++;
				} else if (!locsModifiedFile.get(i).getLine().equals(locsActualFile.get(i).getLine())) {
					/* Modified file line is marked as ADDED */
					line.setChangeType(ChangeType.ADDED);							
					locs.add(line);
					lineNumber++;
					totalAdded++;
					/* Actual file line is added to modified file lines and marked DELETED */
					LineCode deletedLine = (LineCode) locsActualFile.get(i).clone();
					deletedLine.setLineNumber(lineNumber);
					deletedLine.setChangeType(ChangeType.DELETED);
					locs.add(deletedLine);
					lineNumber++;
					totalDeleted++;
				}
			}
			
			
		} catch (Exception e) {
			throw new AnalyzerServiceException("Excepción al comparar la líneas de la clase" + modifiedLOCFile.getName(), e.getCause());
		}
				
		LOCFile locFile = new LOCFile();
		locFile.setLocs(locs);
		locFile.setName(modifiedLOCFile.getName());
		locFile.setTotal(locs.size());
		locFile.setTotalAdded(totalAdded);
		locFile.setTotalDeleted(totalDeleted);
		
		return locFile;
	}
	
	/**
	 * This method is responsible for updating LOCFile instance to mark all lines as added
	 * @param New LOCFile
	 */
	private void addNewLOCFile(LOCFile locFile) {
		List<LineCode> codeLines = locFile.getLocs();
		for (LineCode lineCode : codeLines) {
			lineCode.setChangeType(ChangeType.ADDED);
		}
		locFile.setTotalAdded(codeLines.size());
		locFile.setTotal(codeLines.size());
		locFile.setTotalDeleted(BigInteger.ZERO.intValue());
	}
	
	/**
	 * This method is responsible for updating LOCFile instance to mark all lines as deleted
	 * @param Deleted LOCFile
	 */
	private void addDeletedLOCFile(LOCFile locFile) {
		List<LineCode> codeLines = locFile.getLocs();
		for (LineCode lineCode : codeLines) {
			lineCode.setChangeType(ChangeType.DELETED);
		}
		locFile.setTotalAdded(BigInteger.ZERO.intValue());
		locFile.setTotal(codeLines.size());
		locFile.setTotalDeleted(codeLines.size());
	}
}

