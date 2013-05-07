package com.uniandes.gaudi.change.counter.file.service;

import java.io.File;

import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.file.exception.FileServiceException;
import com.uniandes.gaudi.change.counter.util.file.FileUtil;

/**
 * This class implements the file service for every language
 * 
 * @class LanguageFileService.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class LanguageFileService implements FileService {

	/**
	 * Stores a parser for the source file in a specific language
	 */
	private LOCFileParser locFileParser;
	/**
	 * Stores a file filter to loop the source code, for a specific language
	 */
	private FileFilter fileFilter;
	/**
	 * Stores the initial path 
	 */
	private String initialPath;
	/**
	 * Constructor for the language file service that receives a loc file parser instance
	 * 
	 * @param locFileParser instance to parse the code files into object structure
	 */
	public LanguageFileService(LOCFileParser locFileParser, FileFilter fileFilter) {
		super();
		this.locFileParser = locFileParser;
		this.fileFilter = fileFilter;
	}
	/**
	 * This method exposes the logic to read the file and generate a loc file structure
	 *  
	 * @param projectPath for the given project 
	 * @return LOCFileStructure with the files and loc info
	 * @throws FileServiceException when an error reading the file is thrown
	 */
	@Override
	public LOCFileStructure readModifiedFile(String projectPath)
			throws FileServiceException {

		LOCFileStructure locFileStructure = new LOCFileStructure();
		
		locFileStructure.setPath(projectPath);
		
		File projectFile = new File(projectPath);
		
		initialPath = projectPath;
		
		buildLOCModifiedFileStructure(projectFile, locFileStructure);
		
		return locFileStructure;
	}

	/**
	 * This method exposes the logic to read the file and generate a loc file structure
	 *  
	 * @param projectPath for the given project 
	 * @return LOCFileStructure with the files and loc info
	 * @throws FileServiceException when an error reading the file is thrown
	 */
	@Override
	public LOCFileStructure readFile(String projectPath)
			throws FileServiceException {

		LOCFileStructure locFileStructure = new LOCFileStructure();
		
		locFileStructure.setPath(projectPath);
		
		File projectFile = new File(projectPath);
		
		initialPath = projectPath;
		
		buildLOCFileStructure(projectFile, locFileStructure);
		
		return locFileStructure;
	}
	/**
	 * This is a recursive method where all the files in the source path are
	 * searched and parsed to classFile objects
	 * 
	 * if the file is a folder, loops through the folder searching files
	 * if the file is a file in the system, with the accepted extension, it is parsed 
	 * to a file structure  
	 * 
	 * @param rootFile represents the root file to be parsed
	 * @param locFileStructure with the file structure
	 * @throws FileServiceException when a io  error is throw
	 */
	private void buildLOCModifiedFileStructure(File rootFile, LOCFileStructure locFileStructure) throws FileServiceException {
		
		if (rootFile.isDirectory()) {

			File files[] = rootFile.listFiles(fileFilter);

			for (File file : files) {
				buildLOCModifiedFileStructure(file, locFileStructure);
			}
		}

		if (rootFile.isFile()) {
			LOCFile locFile = locFileParser.parseFile(rootFile);
			
			FileUtil fileUtil = FileUtil.getInstance();
			
			String path = fileUtil.getRelativeParentPath(rootFile, initialPath);
			
			locFileStructure.addLOCFile(path, fileUtil.getFileName(rootFile), locFile);
		}
	}
	/**
	 * This is a recursive method where all the files in the source path are
	 * searched and parsed to classFile objects
	 * 
	 * if the file is a folder, loops through the folder searching files
	 * if the file is a file in the system, with the accepted extension, it is parsed 
	 * to a file structure  
	 * 
	 * @param rootFile represents the root file to be parsed
	 * @param locFileStructure with the file structure
	 * @throws FileServiceException when a io  error is throw
	 */
	private void buildLOCFileStructure(File rootFile, LOCFileStructure locFileStructure) throws FileServiceException {
		
		if (rootFile.isDirectory()) {

			File files[] = rootFile.listFiles(fileFilter);

			for (File file : files) {
				buildLOCFileStructure(file, locFileStructure);
			}
		}

		if (rootFile.isFile()) {
			LOCFile locFile = locFileParser.parseFile(rootFile);
			
			FileUtil fileUtil = FileUtil.getInstance();
			
			String path = fileUtil.getRelativeParentPath(rootFile, initialPath);
			
			locFileStructure.addLOCFile(path, fileUtil.getFileName(rootFile), locFile);
		}
	}
	
}
