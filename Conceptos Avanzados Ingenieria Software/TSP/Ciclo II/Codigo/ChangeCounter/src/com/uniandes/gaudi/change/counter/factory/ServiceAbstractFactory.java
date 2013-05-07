package com.uniandes.gaudi.change.counter.factory;

import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.entity.Language;
import com.uniandes.gaudi.change.counter.file.service.FileService;
import com.uniandes.gaudi.change.counter.java.factory.JavaServiceFactory;
import com.uniandes.gaudi.change.counter.modification.service.ModificationService;
import com.uniandes.gaudi.change.counter.statistics.service.StatisticsService;

/**
 * This class is an abstract factory to instantiate the modules depending on the language
 * 
 * @class ServiceAbstractFactory.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public abstract class ServiceAbstractFactory {

	/**
	 * Singleton instance for this class
	 */
	private static ServiceAbstractFactory instance;
	
	/**
	 * This method returns an instance for this class 
	 * 
	 * @return
	 */
	public static ServiceAbstractFactory getInstance(Language language) {
		
		if (instance == null) {
			switch (language) {
			case JAVA:
				instance = new JavaServiceFactory();
				break;

			default:
				throw new IllegalStateException(String.format("The language %s is not supported", language));
			}
		}
		
		return instance;
	}
	
	/**
	 * This method returns an instance for the analyzer service
	 * 
	 * @return analyzer service instance
	 */
	public abstract AnalyzerService getAnalyzerService();
	/**
	 * This method returns an instance for the file service
	 * 
	 * @return file service instance
	 */
	public abstract FileService getFileService();
	/**
	 * This method returns an instance for the file service
	 * 
	 * @return file service instance
	 */
	public abstract FileService getModifiedFileService();
	/**
	 * This method returns an instance for the modification service
	 * 
	 * @return modification service instance
	 */
	public abstract ModificationService getModificationService();
	/**
	 * This method returns an instance for the statistics service
	 * 
	 * @return statistics service instance
	 */
	public abstract StatisticsService getStatisticsService();


}
