package com.uniandes.gaudi.change.counter.ui.console;

import java.io.Console;

/**
 * This class is a prototype for a User interface console to input change
 * information data
 * 
 * @class ChangeCounterConsole.java
 * @author Felipe
 * @Date Apr 19, 2013
 * @since 1.0
 */
public class ChangeCounterConsole {

	public void invokeApplication(String args[]) {

		String param = args[0];

		if (param.equals("-stat")) {
			System.out.println(String.format("Statistics generated in %s\\%s", args[1], "statitics"));
		}

		if (param.equals("-modlist")) {
			System.out.println(String.format("Modifications list generated in %s\\%s", args[1], "modification list"));
		}
		
		if (param.equals("-modhead")) {
			System.out.println(String.format("Header modifications list generated in %s\\%s", args[1], "heads lists"));
		}

		if (param.equals("-comp")) {
			
			String modificationType = args[1];
			
			if (modificationType.equals("-fix")) {
				System.out.println("Version fix compare");
			}
			
			if (modificationType.equals("-imp")) {
				System.out.println("Version improvement compare");
			}
			
			getCompareInfo();
		}
	}
	
	private void getCompareInfo() {

		Console console = System.console();

		String initialPath = console.readLine("Input initial version path\n", "");

		String modifiedPath = console.readLine("Input modified version path\n", "");

		String developerName = console.readLine("Input developer\n", "");

		String comments = console.readLine("Input comments\n", "");

		System.out.println("version compared succesfully");
	}
}
