package com.uniandes.gaudi.change.counter.main;

import com.uniandes.gaudi.change.counter.controller.exception.ControllerServiceException;
import com.uniandes.gaudi.change.counter.controller.service.ControllerServiceImpl;
import com.uniandes.gaudi.change.counter.entity.CompareInfo;


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
		//An instance of CompareInfo that will send a request to the Controller
		CompareInfo compareInfo = new CompareInfo();
		compareInfo.setActualPath(args[0]);
		compareInfo.setModifiedPath(args[1]);
		
		//Now is time to instance the Controller
		ControllerServiceImpl controller = new ControllerServiceImpl();
		try {
			controller.compareVersions(compareInfo);
		} catch (ControllerServiceException e) {
			e.printStackTrace();
		}
		
//		String regex = "(public|protected|private)? +((\\w)+ +)*(class|interface|enum) +(\\w+)( *.*)*\\{";
//		System.out.println(Pattern.compile(regex).matcher("public abstract class jdjdjdjdj imp kjdf,dfd{").matches());
	}
}
