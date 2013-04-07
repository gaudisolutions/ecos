package com.uniandes.gaudi.change.counter.java.file;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;

import com.uniandes.gaudi.change.counter.entity.BlockLOC;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LineCode;
import com.uniandes.gaudi.change.counter.entity.LineType;
import com.uniandes.gaudi.change.counter.file.service.LOCFileParser;

/**
 * This class implements the logic to parse a java source file 
 * 
 * @class JavaLOCFileParser.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class JavaLOCFileParser implements LOCFileParser {

	/**
	 * Method to parse a file in a given language into a LOCFile
	 *  
	 * @param file path for the source file
	 * @return LOCFile with the info 
	 */
	@Override
	public LOCFile parseFile(File file) {

		return null;
	}

	/**
	 * This method parses a java file and fills the loc file object
	 * 
	 * @param file to be parsed
	 * @param locFile object with the data
	 */
	private void parseFile(File file, LOCFile locFile) {
		
	}
	
	/**
	 * returns true if the line does not end with }, // or if the line is empty
	 *  
	 * @param line to be evaluated
	 * 
	 * @return true if the line is valid, false otherwise
	 */
	private Boolean isValidLine(String line) {
		
		return Boolean.FALSE;
	}
	
	/**
	 * returns true if the line represents a java method using the regular expression
	 * (public|protected|private)? +(\\w+ +)*(.*) +\\w+ *\\(.*\\) *(throws \\w+ *)?\\{
	 * 
	 * @param line to be evaluated
	 * @return true if the lines a method, false otherwise
	 */
	private Boolean isMethod(String line) {
		return Boolean.FALSE;
	}
	
	/**
	 * returns true if the line represents a java method using the regular expression
	 * (public|protected|private)? +%s *\\(.*\\) *(throws +\\w+ *)?\\{ where %s should be 
	 * replaced with the class name
	 * 
	 * @param line to be avluated
	 * @param className name for this class
	 * @return true if the line is a constructor, false otherwise
	 */
	private Boolean isConstructor(String line, String className) {
		return Boolean.FALSE;
	}
	/**
	 * returns true if the line represents a java doc comment, if the line starts with
	 * /**, * or * /
	 *  
	 * @param line
	 * @return
	 */
	private Boolean isComment(String line) {
		
		return Boolean.FALSE;
	}
	/**
	 * This method fills the the lines of code block of code like method constructor, or 
	 * any other structure, to do this, the method searches the clossing } for the initial {
	 * where the block is declared
	 * 
	 * @param locFile
	 * @param bufferedReader
	 * @param lineNumber line number for this code
	 * @param lineType line type for this loc
	 */
	private void fillBlock(BlockLOC loc, BufferedReader bufferedReader, Integer lineNumber, LineType lineType) {
	}
	
	/**
	 * This method creates a loc
	 * 
	 * @param line in the file
	 * @param lineNumber 
	 * @param type
	 * @return LineCode with the line info
	 */
	private LineCode createLOC(String line, Integer lineNumber, LineType type) {
		
		return null;
	}
	/**
	 * loops through the closeable elements, and invokes the close method, if 
	 * element is null, the loop continues
	 *  
	 * @param closeable elements to be closed
	 */
	private void closeStreams(Closeable closeable) {
		
	}

}
