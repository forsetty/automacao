package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private static Properties properties;
	
	static {
		loadProperties();
	};
	
	public static void loadProperties() {
		properties = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(
					"..\\automacao-mantis\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUsuarioAplicacao() {
		return properties.getProperty("usuarioAplicacao");
	}
	
	public static String getSenhaAplicacao() {
		return properties.getProperty("senhaAplicacao");
	}
	
	public static String getUrlAplicacao() {
		return properties.getProperty("urlAplicacao");
	}
	
	public static String getBrowser() {
		return properties.getProperty("browser");
	}
}
