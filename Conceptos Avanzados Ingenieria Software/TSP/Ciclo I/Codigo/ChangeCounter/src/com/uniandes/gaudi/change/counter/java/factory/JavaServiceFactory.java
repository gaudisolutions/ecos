package com.uniandes.gaudi.change.counter.java.factory;

import com.uniandes.gaudi.change.counter.analyzer.service.AnalyzerService;
import com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory;
import com.uniandes.gaudi.change.counter.file.service.FileService;
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

	/* (non-Javadoc)
	 * @see com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory#getAnalyzerService()
	 */
	@Override
	public AnalyzerService getAnalyzerService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory#getFileService()
	 */
	@Override
	public FileService getFileService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory#getModificationService()
	 */
	@Override
	public ModificationService getModificationService() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.uniandes.gaudi.change.counter.factory.ServiceAbstractFactory#getStatisticsService()
	 */
	@Override
	public StatisticsService getStatisticsService() {
		// TODO Auto-generated method stub
		return null;
	}

}
