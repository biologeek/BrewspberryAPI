package net.brewspberry.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {
	
	
	private static Logger logger;
	private static Handler handler;

	public static Logger getInstance(String name){
		
		if (logger == null){
			handler = new ConsoleHandler();
			logger = Logger.getLogger(name);
			logger.addHandler(handler);
			
		}
		
		return logger;
	}
	
	public static void setLevel (){
		
		if (logger != null){
			logger.setLevel(Level.ALL);
		}
	}

}
