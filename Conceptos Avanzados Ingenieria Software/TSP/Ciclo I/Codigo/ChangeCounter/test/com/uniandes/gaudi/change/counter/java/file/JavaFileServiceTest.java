package com.uniandes.gaudi.change.counter.java.file;

import java.io.File;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;

/**
 * @class JavaFileServiceTest.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class JavaFileServiceTest {

	@Test
	public void parseFileTest() {
		
		JavaLOCFileParser fileParser = new JavaLOCFileParser();
		
		try {
			fileParser.parseFile(new File("C:\\Users\\Felipe\\Google Drive\\Andes ECOS\\git\\Conceptos Avanzados Ingenieria Software\\TSP\\Ciclo I\\Codigo\\ChangeCounter\\test\\com\\uniandes\\gaudi\\change\\counter\\java\\file\\TestClass.java"));
			
//			Ass
			
		} catch (FileServiceException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}
}
