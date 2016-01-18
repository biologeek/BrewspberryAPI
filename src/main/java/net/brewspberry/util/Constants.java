package net.brewspberry.util;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public abstract class Constants {

	public static String BLOND = "Blonde";
	public static String BRUNE = "Brune";
	public static String BLANCHE = "Blanche";
	public static String IPA = "Blonde";
	public static String ALE = "Blonde";
	public static String STOUT = "Blonde";
	public static String LAGER = "Blonde";
	
	
	public static int ACT_RUNNING = 10;
	public static int ACT_PAUSED = 5;
	public static int ACT_STOPPED = 1;

	
	public static String ACT_DS18B20 = "ds18b20";
	public static String ACT_PUMP = "pump";
	public static String ACT_ENGINE = "engine";
	
	//	public static String PROJECT_ROOT_PATH = "/home/pi";
	public static String PROJECT_ROOT_PATH = "/opt/tomcat/webapps";
	public static String BREW_VIEWER = "brewspberry-viewer";
	public static String BREW_API = "brewspberry-core";
	public static String BREW_BATCHES = "BrewspberryPi-Batches";
	public static String BREW_TEMP = "brewspberry-api";
	
	public static String DEVICES_PROPERTIES = PROJECT_ROOT_PATH+"/"+BREW_VIEWER+"/WEB-INF/classes/devices.properties";
	
	public static String CONFIG_PROPERTIES = PROJECT_ROOT_PATH+"/conf/config.properties";
	


	public Constants() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static final Map<String, String> BREW_STATUS = new HashMap<String, String>();

	static	{
			BREW_STATUS.put("10", "Brassage");
			BREW_STATUS.put("20", "Fermentation");
			BREW_STATUS.put("30", "Garde");
			BREW_STATUS.put("40", "Embouteillage");
			BREW_STATUS.put("50", "Dégustation");
			BREW_STATUS.put("60", "Terminée");
	};
	
	public static final Map<String, String> ACTIONNER = new HashMap<String, String>();
	
	static	{
			BREW_STATUS.put("1", "ds18b20");
			BREW_STATUS.put("2", "relay");
	};

	
	public static final Map<String, Pin> BREW_GPIO = new HashMap<String, Pin>();
	
	static	{
		BREW_GPIO.put("11", RaspiPin.GPIO_00);
		BREW_GPIO.put("12", RaspiPin.GPIO_01);
		BREW_GPIO.put("13", RaspiPin.GPIO_02);
		BREW_GPIO.put("15", RaspiPin.GPIO_03);
		BREW_GPIO.put("16", RaspiPin.GPIO_04);
		BREW_GPIO.put("18", RaspiPin.GPIO_05);
		BREW_GPIO.put("22", RaspiPin.GPIO_06);
		BREW_GPIO.put("07", RaspiPin.GPIO_07);
		BREW_GPIO.put("03", RaspiPin.GPIO_08);
		BREW_GPIO.put("05", RaspiPin.GPIO_09);
		BREW_GPIO.put("24", RaspiPin.GPIO_10);
		BREW_GPIO.put("26", RaspiPin.GPIO_11);
		BREW_GPIO.put("19", RaspiPin.GPIO_12);
		BREW_GPIO.put("21", RaspiPin.GPIO_13);
		BREW_GPIO.put("23", RaspiPin.GPIO_14);

	};
	
	public static final Map<Pin, String> BREW_GPIO_TO_STR = new HashMap<Pin, String>();
	
	static	{
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_00,"11");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_01,"12");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_02,"13");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_03,"15");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_04,"16");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_05,"18");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_06,"22");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_07,"07");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_08,"03");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_09,"05");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_10,"24");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_11,"26");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_12,"19");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_13,"21");
		BREW_GPIO_TO_STR.put(RaspiPin.GPIO_14,"23");

	};
		
	
}

	