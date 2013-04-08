package com.uniandes.gaudi.change.counter.java.factory;

import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory;
import com.uniandes.gaudi.change.counter.file.service.FileFilter;
import com.uniandes.gaudi.change.counter.file.service.FileService;
import com.uniandes.gaudi.change.counter.file.service.LOCFileParser;
import com.uniandes.gaudi.change.counter.file.service.LanguageFileService;
import com.uniandes.gaudi.change.counter.java.analyzer.service.JavaAnalyzerService;
import com.uniandes.gaudi.change.counter.java.file.JavaLOCFileParser;
import com.uniandes.gaudi.change.counter.modification.service.ModificationService;
import com.uniandes.gaudi.change.counter.statistics.service.StatisticsService;

/**
 * This class implements the logic to get the instances for java service modules
 * 
 * @class JavaServiceFactory.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class JavaServiceFactory extends ServiceAbstractFactory {

	/**
	 * This method returns an instance for the analyzer service
	 * 
	 * @return analyzer service instance
	 */
	@Override
	public AnalyzerService getAnalyzerService() {

		return new JavaAnalyzerService();
	}

	/**
	 * This method returns an instance for the file service with specific 
	 * java parser and file filter
	 * 
	 * @return file service instance
	 */
	@Override
	public FileService getFileService() {

		LOCFileParser locFileParser = new JavaLOCFileParser();
		FileFilter fileFilter = new FileFilter(".java");
		
		LanguageFileService fileService = new LanguageFileService(locFileParser, fileFilter);
		
		return fileService;
	}

	/**
	 * This method returns an instance for the modification service
	 * 
	 * @return modification service instance
	 */
	@Override
	public ModificationService getModificationService() {

		return null;
	}

	/**
	 * This method returns an instance for the statistics service
	 * 
	 * @return statistics service instance
	 */
	@Override
	public StatisticsService getStatisticsService() {

		return null;
	}

}
