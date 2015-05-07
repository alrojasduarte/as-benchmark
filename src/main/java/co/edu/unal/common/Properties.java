/**
 * 
 */
package co.edu.unal.common;

import java.io.IOException;

/**
 * @author Andres
 *
 */
public class Properties {

	private static Properties INSTANCE;
	
	private java.util.Properties properties;
	
	public static Properties getInstance() throws IOException{
		if(INSTANCE==null){
			INSTANCE = new Properties();
			INSTANCE.properties = new java.util.Properties();
			INSTANCE.properties.load(Properties.class.getClassLoader().getResourceAsStream(Constants.APPLICATION_ENVIRONMENT_PROPERTY_FILE_NAME));
		}
		return INSTANCE;
	}
	
	public String getProperty(String key){
		return properties.getProperty(key);
	}
}
