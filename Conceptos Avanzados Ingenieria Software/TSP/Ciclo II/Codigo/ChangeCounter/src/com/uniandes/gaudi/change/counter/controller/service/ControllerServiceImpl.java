package com.uniandes.gaudi.change.counter.controller.service;


import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.controller.exception.ControllerServiceException;

import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.entity.Language;
import com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;
import com.uniandes.gaudi.change.counter.file.service.FileService;



import com.uniandes.gaudi.change.counter.java.analyzer.service.JavaAnalyzerService;
import com.uniandes.gaudi.change.counter.java.statistics.JavaStatisticsService;
import com.uniandes.gaudi.change.counter.modification.exception.ModificationServiceException;
import com.uniandes.gaudi.change.counter.modification.service.ModificationService;
import com.uniandes.gaudi.change.counter.statistics.service.StatisticsService;


/**
 * This class is in charge to implement the logic for the contollerService,
 * and is in charge to orchestrate invocations to other modules  
 * 
 * @class ControllerServiceImpo.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class ControllerServiceImpl implements ControllerService {

	/**
	 * This method exposes the logic to compare to versions of a program
	 * 1. calls the FileService to read the files and create a object model
	 * structure
	 * 2. calls the AnalyzerService to analyze changes between files
	 * 3. calls the ModificationService to store file labels with modification
	 * information.
	 * 

	 * 
	 * @param compareInfo with the user, change reason, modification type
	 * @throws ControllerServiceException when a controller service exception is thrown
	 */
	@Override
	public void compareVersions(CompareInfo compareInfo)
			throws ControllerServiceException {

		try{
			if (compareInfo == null) {
				throw new IllegalArgumentException("compare info is null");
			}
			ServiceAbstractFactory serviceFactory = ServiceAbstractFactory.getInstance(Language.JAVA);
			
			FileService fileService = serviceFactory.getFileService();
			AnalyzerService analyzerService = serviceFactory.getAnalyzerService();
			ModificationService modificationService = serviceFactory.getModificationService();
			
			LOCFileStructure actualFileStructure = fileService.readFile(compareInfo.getActualPath());
			LOCFileStructure modifiedFileStructure = fileService.readFile(compareInfo.getModifiedPath());
			
			ChangeLOCStructure changeLOCStructure = analyzerService.performAnalysis(actualFileStructure, modifiedFileStructure);
			
			changeLOCStructure.setCompareInfo(compareInfo);	
			
			modificationService.performLabelRegistry(changeLOCStructure);
			modificationService.performHeader(modifiedFileStructure,changeLOCStructure);
		
		} catch (FileServiceException e) {
			e.printStackTrace();
		} catch (AnalyzerServiceException e) {
			e.printStackTrace();
		} catch (ModificationServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * This method exposes the logic to generate statistics
	 * 
	 * @param projectPath with the change label info
	 * @param destinationPath 
	 * @throws ControllerServiceException when a controller service exception is thrown 
	 */
	@Override
	public void generateStatistics(String projectPath, String destinationPath)
			throws ControllerServiceException {
		try{
			if (projectPath == null) {
				throw new IllegalArgumentException("compare info is null");
			}
			ServiceAbstractFactory serviceFactory = ServiceAbstractFactory.getInstance(Language.JAVA);
			
			FileService fileService = serviceFactory.getModifiedFileService();
			
			LOCFileStructure modifiedFileStructure = fileService.readModifiedFile(projectPath);
			
			StatisticsService statisticsService = serviceFactory.getStatisticsService();
			statisticsService.generateStatistics(modifiedFileStructure,destinationPath);
		} catch (FileServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
