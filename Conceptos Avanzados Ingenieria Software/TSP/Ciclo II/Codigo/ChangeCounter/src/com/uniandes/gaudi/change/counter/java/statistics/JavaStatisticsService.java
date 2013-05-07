package com.uniandes.gaudi.change.counter.java.statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.uniandes.gaudi.change.counter.analyzer.exception.AnalyzerServiceException;
import com.uniandes.gaudi.change.counter.entity.ChangeLOCStructure;
import com.uniandes.gaudi.change.counter.entity.ChangeStatistics;
import com.uniandes.gaudi.change.counter.entity.ChangeType;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;
import com.uniandes.gaudi.change.counter.entity.LOCFile;
import com.uniandes.gaudi.change.counter.entity.LOCFileStructure;
import com.uniandes.gaudi.change.counter.entity.LineCode;
import com.uniandes.gaudi.change.counter.modification.exception.ModificationServiceException;
import com.uniandes.gaudi.change.counter.statistics.exception.StatisticsServiceException;
import com.uniandes.gaudi.change.counter.statistics.service.StatisticsService;
import com.uniandes.gaudi.change.counter.util.file.FileUtil;

public class JavaStatisticsService implements StatisticsService{

	@Override
	public void generateStatistics(LOCFileStructure modifiedFileStructure, String destinationPath)
			throws StatisticsServiceException {
		
		
		if ( modifiedFileStructure == null) {
			throw new StatisticsServiceException("Las estructuras de archivos a sacar las estadisticas no pueden ser null");
		}
		
		
			
			
		Map<String, Map<String, LOCFile>> modifiedPackageFiles = modifiedFileStructure.getPackageFiles();
		
		StringBuilder lineContent=new StringBuilder();
		Set<String> modifiedPackages = modifiedPackageFiles.keySet();
		Set<String> allPackages = new HashSet<String>(); 
		
		allPackages.addAll(modifiedPackages);
		try {
			
			for (String packageName : allPackages) {
				
				
				
				
					
					
					
					Map<String, LOCFile> modifiedLOCFiles = modifiedPackageFiles.get(packageName);
					
					Set<String> modifiedClases = modifiedLOCFiles.keySet();
					Set<String> allClasses = new HashSet<String>(); 
					
					allClasses.addAll(modifiedClases);
					
					for (String className : allClasses) {
						
							List<LineCode> locsModifiedFile = modifiedLOCFiles.get(className).getLocs();								
							
							int totalAdded = 0;
							int totalDeleted = 0;
							int totalAdded2 = 0;
							int totalDeleted2 = 0;
							double version=0;
							double version2=0;
								
							lineContent.append("CLASS :<");
							lineContent.append( modifiedLOCFiles.get(className).getName()+">{\r\n");
							StringBuilder lines=new StringBuilder();
							StringBuilder lines2=new StringBuilder();
							//Busca las versiones

							
								for (int i = 0; i < locsModifiedFile.size(); i++) {
								
								
									String vec[];

									String line="";
									
									
									
									if (locsModifiedFile.get(i)!=null) {
										line =  locsModifiedFile.get(i).getLine();
										//System.out.println(locsModifiedFile.get(i).getLine().toString());
										vec=line.split("\t");
										
										JSONObject jsonObject = new JSONObject(vec[0]);
										if(i==0){
											version=jsonObject.getDouble("VERSION");
										}
										
										if(version!=jsonObject.getDouble("VERSION")){
											version2=jsonObject.getDouble("VERSION");
											if(isDeleted(line)){
												totalDeleted2++;
												}
												if(isAdded(line)){
												totalAdded2++;
												}
												lines2.append("<"+line.split("\t")[1]+">\r\n");
										}
										
										
										else{
											if(isDeleted(line)){
											totalDeleted++;
											}
											if(isAdded(line)){
											totalAdded++;
											}
											lines.append("<"+line.split("\t")[1]+">\r\n");
										}
										
										

									} 
									

								}
								lineContent.append("VERSION :<"+version +">{\r\n");
								lineContent.append("LOCS AGREGADOS :<"+totalAdded +">\r\n"); 
								lineContent.append("LOCS ELIMINADOS :<"+totalDeleted +">\r\n");
								lineContent.append("LINEAS:" +"\r\n");
								lineContent.append(lines.toString());
								lineContent.append("}\r\n\r\n");
								if(version2!=0){
								lineContent.append("VERSION :<"+version2 +">{\r\n");
								lineContent.append("LOCS AGREGADOS :<"+totalAdded2 +">\r\n"); 
								lineContent.append("LOCS ELIMINADOS :<"+totalDeleted2 +">\r\n\r\n");
								lineContent.append("LINEAS:" +"\r\n");
								lineContent.append(lines2.toString());
								lineContent.append("}\r\n");
								lineContent.append("}\r\n\r\n");
								}
								else{
									lineContent.append("}\r\n\r\n");
								}

					}
					
					
					
				

			}
			createFile(lineContent,destinationPath);
		} catch (Exception e) {
			throw new StatisticsServiceException("Excepción buscar las clases en el paquete" + modifiedFileStructure.getPath(), e.getCause());
		}
		

		
	}
	/**
	 * This method create the new file with the estatistics
	 * @param destinationPath 
	 * @param location
	 * @param labelLine
	 * @throws StatisticsServiceException 
	 * @throws ModificationServiceException 
	 */
	public void createFile(StringBuilder fileContent, String destinationPath ) throws StatisticsServiceException {
		File newFile = new File(destinationPath+"\\Estadisticas.txt");
		FileWriter writer = null;
		try {

			writer = new FileWriter(newFile);
			

				
			
			
			writer.append(fileContent.toString());
			writer.flush();
			
		} catch (IOException e) {
			throw new StatisticsServiceException(e.getMessage(),e);
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
		
	}

	private boolean isAdded(String line) {
		String expression = String.format(".*?DELETED+.*");
		return evaluateExpression( line,expression);
	}

	private boolean isDeleted(String line) {
		String expression = String.format(".*?ADDED+.*");
		return evaluateExpression( line,expression);
	}
	/**
	 *  This method evaluates a regex expression
	 * @param regex to compare
	 */
	private Boolean evaluateExpression( String line,String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		return matcher.matches();
	}

}
