package com.uniandes.gaudi.change.counter.java.file;

import java.io.File;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Test;

import com.uniandes.gaudi.change.counter.entity.LOCFile;
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
			LOCFile locFile = fileParser.parseFile(new File("test\\com\\uniandes\\gaudi\\change\\counter\\java\\file\\TestClass.java"));
			
			Assert.assertEquals("deben ser iguales", "TestClass", locFile.getName());
			
		} catch (FileServiceException e) {
			throw new AssertionFailedError(e.getMessage());
		}
	}
}
