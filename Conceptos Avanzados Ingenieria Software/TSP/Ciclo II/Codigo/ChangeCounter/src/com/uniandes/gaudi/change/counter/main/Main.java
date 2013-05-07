/*
*<PreviousChangeCounter>
*<PreviousVERSION> 2.0
*<PreviousAUTHOR> Juan Pedro Mendoza R.
*<PreviousCHANGES_DATE> Tue May 07 10:23:36 COT 2013
*<PreviousCHANGES_REASON> Por que quiero puedo y no me da miedo
*<PreviousTOTAL_LOCS> 2955
*<PreviousTOTAL_LOCS_ADDED> 1072
*<PreviousTOTAL_LOCS_DELETED> 857
*/
package com.uniandes.gaudi.change.counter.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.uniandes.gaudi.change.counter.controller.exception.ControllerServiceException;
import com.uniandes.gaudi.change.counter.controller.service.ControllerServiceImpl;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;
import com.uniandes.gaudi.change.counter.entity.ModificationType;



/**
 * This is the main class for this program
 * 
 * @class Main.java
 * @author Felipe
 * @Date 7/04/2013
 * @since 1.0
 */
public class Main {

	/**
	 * Main method for the program
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader buff;
		String vec[];
		String line;
		CompareInfo compareInfo = new CompareInfo();
		boolean option=false;
		
		try{
			buff=new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Ingrese C si desea hacer una comparacion o E si desea ver las estadisticas una comparacion anterior");
			
			if((line=buff.readLine()).equalsIgnoreCase("c")){
				option=true;
			}
			if(line.equalsIgnoreCase("e")){
				option=true;
			}
			while(!option){
				System.out.println("El comando ingresado no es una opcion: C=comparación/E=estadisticas");
				if((line=buff.readLine()).equalsIgnoreCase("c")){
					option=true;
				}
				if(line.equalsIgnoreCase("e")){
					option=true;
				}
				
			}
			if(line.equalsIgnoreCase("c")){
			System.out.println("Ingrese el Nombre de la persona que hizo las modificaciones");
			line=buff.readLine();
			compareInfo.setModifiedBy(line);
			System.out.println("Ingrese el tipo de modificacion.\nFIX\nENHACENMENT");
			line=buff.readLine();			
			compareInfo.setModificationType(line.equalsIgnoreCase("fix")? ModificationType.FIX:ModificationType.ENHACENMENT);
			System.out.println("Explique la razon por la cual se realizaron los cambios");
			line=buff.readLine();
			compareInfo.setChangeReason(line);
			System.out.println("Ingrese la ruta del proyecto sin modificaciones");
			line=buff.readLine();
			compareInfo.setActualPath(line);
			System.out.println("Ingrese la ruta del proyecto modificado");
			line=buff.readLine();
			compareInfo.setModifiedPath(line);
			ControllerServiceImpl controller=new ControllerServiceImpl();
			controller.compareVersions(compareInfo);
			System.out.println("La comparacion a finalizado, ingrese a la carpeta modified de su proyecto para ver los cambios");
			}
			else{
				String destinationPath="";
				String modifiedPath="";
				System.out.println("Ingrese la ruta de la carpeta modified");
				line=buff.readLine();
				modifiedPath=line;
				System.out.println("Ingrese la ruta de la carpeta donde desea crear el archivo");
				line=buff.readLine();
				destinationPath=line;
				ControllerServiceImpl controller=new ControllerServiceImpl();
				controller.generateStatistics(modifiedPath,destinationPath);
				System.out.println("Su archivo se creo en la ruta: "+destinationPath);
				
			}
			buff.close();

		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		catch (ControllerServiceException e) {
			
			e.printStackTrace();
		}
		
		//compareInfo.setActualPath(args[0]);
		//compareInfo.setModifiedPath(args[1]);
		
		//Now is time to instance the Controller
		//ControllerServiceImpl controller = new ControllerServiceImpl();
		//try {
		//	controller.compareVersions(compareInfo);
		//} catch (ControllerServiceException e) {
		//	e.printStackTrace();
		//}
		
//		String regex = "(public|protected|private)? +((\\w)+ +)*(class|interface|enum) +(\\w+)( *.*)*\\{";
//		System.out.println(Pattern.compile(regex).matcher("public abstract class jdjdjdjdj imp kjdf,dfd{").matches());
	}
}
