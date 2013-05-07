package com.uniandes.gaudi.change.counter.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a line of code (LOC)
 * 
 * @class LineCode.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class LineCode implements Cloneable {

	/**
	 * Represents the change type for this loc
	 */
	private ChangeType changeType;
	/**
	 * Stores the line in the file
	 */
	private String line;
	/**
	 * Stores the line number
	 */
	private Integer lineNumber;
	/**
	 * Stores the line type 
	 */
	private LineType lineType;
	/**
	 * @return the changeType
	 */
	public ChangeType getChangeType() {
		return changeType;
	}
	/**
	 * @param changeType the changeType to set
	 */
	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}
	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}
	/**
	 * @return the lineNumber
	 */
	public Integer getLineNumber() {
		return lineNumber;
	}
	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	/**
	 * @return the lineType
	 */
	public LineType getLineType() {
		return lineType;
	}
	/**
	 * @param lineType the lineType to set
	 */
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	/**
	 * @return true if the line represents the main method
	 */
	public boolean isMain() {
		
		String expression = String.format("(public|protected|private)? +static +(void|int)? +main+.*");
		return evaluateExpression( expression);
		
	}
	/**
	 *  This method evaluates a regex expression
	 * @param regex to compare
	 */
	private Boolean evaluateExpression( String regex) {
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		return matcher.matches();
	}
}
