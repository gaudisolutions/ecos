package com.uniandes.gaudi.change.counter.java.file;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.uniandes.gaudi.change.counter.entity.BlockLOC;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LineCode;
import com.uniandes.gaudi.change.counter.entity.LineType;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;
import com.uniandes.gaudi.change.counter.file.service.LOCFileParser;
import com.uniandes.gaudi.change.counter.util.file.FileUtil;

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
	public LOCFile parseFile(File file) throws FileServiceException {

		LOCFile locFile = new LOCFile();
		locFile.setPath(file.getAbsolutePath());
		parseFile(file, locFile);
		
		return locFile;
	}

	/**
	 * This method parses a java file and fills the loc file object
	 * 
	 * @param file to be parsed
	 * @param locFile object with the data
	 */
	private void parseFile(File file, LOCFile locFile) throws FileServiceException {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		List<LineCode> locs = new ArrayList<LineCode>();
		locFile.setLocs(locs);

		try {
			String fileName = FileUtil.getInstance().getFileName(file);

			locFile.setName(fileName);

			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			String line = bufferedReader.readLine();
			
			int lineNumber = 1;
			
			while (line != null) {

				line = line.trim();

				if (isValidLine(line)) {
					
					Boolean isConstructor = isConstructor(line, locFile.getName());
					Boolean isMethod = isMethod(line);
					Boolean isClass = isClass(line);
					
					LineType lineType = getLineType(isMethod, isConstructor, isClass);
					
					if (isMethod || isConstructor || isClass) {
//						BlockLOC blockLOC = new BlockLOC();
//						lineNumber = fillBlock(blockLOC, bufferedReader, line, lineNumber, lineType);
						LineCode loc = new LineCode();
						fillLOC(loc, line, lineNumber, lineType);
						locs.add(loc);
					} else if (isComment(line)) {
						LineCode loc = new LineCode();
						fillLOC(loc, line, lineNumber, LineType.COMMENT);
						locs.add(loc);
					}
					else {
						
						LineCode loc = new LineCode();
						fillLOC(loc, line, lineNumber, LineType.REGULAR_LINE);
						locs.add(loc);
					}
					
					lineNumber++;
				}

				line = bufferedReader.readLine();
			}

		} catch (FileNotFoundException e) {
			throw new FileServiceException(e.getMessage(), e);
		} catch (IOException e) {
			throw new FileServiceException(e.getMessage(), e);
		} finally {
			closeStreams(bufferedReader, fileReader);
		}
	}
	
	/**
	 * returns the line type for the current line
	 * 
	 * @param isMethod
	 * @param isConstructor
	 * @param isClass
	 * @return the line type for the actual line
	 */
	private LineType getLineType(Boolean isMethod, Boolean isConstructor, Boolean isClass) {
		
		if (isClass) {
			return LineType.CLASS;
		}
		if (isConstructor) {
			return LineType.METHOD;
		}
		if (isMethod) {
			return LineType.METHOD;
		}
		
		return LineType.REGULAR_LINE;
	}

	/**
	 * returns true if the line does not end with }, // or if the line is empty
	 *  
	 * @param line to be evaluated
	 * 
	 * @return true if the line is valid, false otherwise
	 */
	private Boolean isValidLine(String line) {
		
		if (line.isEmpty() || line.equals("}") || line.startsWith("//")) {
			return false;
		}

		return true;
	}

	/**
	 * returns true if the line represents a java class using the regular expression
	 * (public|protected|private)? +((\\w)+ +)*(class|interface|enum) +(\\w+)( *.*)*\\{
	 * 
	 * @param line
	 * @return true if the lines is a class, false otherwise
	 */
	private Boolean isClass(String line) {
		String regex = "(public|protected|private)? +((\\w)+ +)*(class|interface|enum) +(\\w+)( *.*)*\\{";
		
		return evaluateExpression(line, regex);
	}

	/**
	 * returns true if the line represents a java method using the regular expression
	 * (public|protected|private)? +(\\w+ +)*(.*) +\\w+ *\\(.*\\) *(throws \\w+ *)?\\{
	 * 
	 * @param line to be evaluated
	 * @return true if the lines a method, false otherwise
	 */
	private Boolean isMethod(String line) {
		String regex = "(public|protected|private)? +(\\w+ +)*(.*) +\\w+ *\\(.*\\) *(throws \\w+ *)?\\{";

		return evaluateExpression(line, regex);
	}
	
	/**
	 * returns true if the line represents a java method using the regular expression
	 * (public|protected|private)? +%s *\\(.*\\) *(throws +\\w+ *)?\\{ where %s should be 
	 * replaced with the class name
	 * 
	 * @param line to be evaluated
	 * @param className name for this class
	 * @return true if the line is a constructor, false otherwise
	 */
	private Boolean isConstructor(String line, String className) {
		String expression = String.format("(public|protected|private)? +%s *\\(.*\\) *(throws +\\w+ *)?\\{",className);
		return evaluateExpression(line, expression);
	}
	
	/**
	 * This method evaluates a regex expression
	 * 
	 * @param line evaluate
	 * @param regex to compare
	 * @return true if the line matches the regex
	 */
	private Boolean evaluateExpression(String line, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		
		return matcher.matches();
	}
	
	/**
	 * returns true if the line represents a java doc comment, if the line starts with
	 * /**, * or * /
	 *  
	 * @param line
	 * @return
	 */
	private Boolean isComment(String line) {
		
		if (line.startsWith("/**") || line.startsWith("*") || line.endsWith("*/")) {
			return Boolean.TRUE;
		}
		
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
	 * @throws IOException 
	 */
	private Integer fillBlock(BlockLOC loc, BufferedReader bufferedReader, String line, Integer lineNumber, LineType lineType) throws IOException {
		
		List<LineCode> locs = new ArrayList<LineCode>();
		
		fillLOC(loc, line, lineNumber, lineType);
		loc.setLocs(locs);
		
		lineNumber++;
		
		line = bufferedReader.readLine();
		Integer closeLines = 1;
		
		while (closeLines != 0) {
			line = line.trim();
			
			if (isValidLine(line)) {
				if (line.endsWith("{")) {
					closeLines++;
				}
				LineCode lineCode = new LineCode();
				fillLOC(lineCode, line, lineNumber, LineType.REGULAR_LINE);
				locs.add(lineCode);
				
				lineNumber++;
			} else {
				if (line.endsWith("}")) {
					closeLines--;
				}
			}
			
			line = bufferedReader.readLine();	
		}
		
		lineNumber--;
		
		return lineNumber;
	}
	
	/**
	 * This method creates a loc
	 * 
	 * @param line in the file
	 * @param lineNumber 
	 * @param type
	 * @return LineCode with the line info
	 */
	private void fillLOC(LineCode lineCode, String line, Integer lineNumber, LineType type) {
		lineCode.setLine(line);
		lineCode.setLineNumber(lineNumber);
		lineCode.setLineType(type);
	}
	
	/**
	 * loops through the closeable elements, and invokes the close method, if 
	 * element is null, the loop continues
	 *  
	 * @param closeable elements to be closed
	 */
	private void closeStreams(Closeable... closeables) {
		for (Closeable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

}
