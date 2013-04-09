package com.uniandes.gaudi.change.counter.util.file;

import java.io.File;

/**
 * This a utility class to manage file objects
 * 
 * @class FileUtil.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class FileUtil {
	
	/**
	 * Singleton instance for this class
	 */
	private static FileUtil instance;
	
	/**
	 * returns the singleton instance of the class
	 * @return
	 */
	public static FileUtil getInstance() {
		if (instance == null) {
			instance = new FileUtil();
		}
		
		return instance;
	}
	
	/**
	 * This method returns a file name without the extension,
	 * with a substring from the first character to the last .
	 * 
	 * @param file 
	 * @return file name without extension
	 */
	public String getFileName(File file) {
		
		String fileName = file.getName();
		
		fileName = fileName.substring(0, fileName.indexOf("."));
		
		return fileName;
	}
	
	/**
	 * This method returns an relative path for a file from a 
	 * given root file, returns the entire path without the root
	 * 
	 * @param file  
	 * @param root path 
	 * @return relative path
	 */
	public String getRelativeParentPath(File file, String root) {
		
		String path = file.getParent();
		path = path.replace(root, "");
		
		if(path.startsWith("\\")){
			path= path.substring(1);
		}
		
		
		return path;
	}
}
