/**
 * 
 */
package co.edu.unal.common;

import java.io.IOException;

/**
 * @author Andres
 *
 */
public class CurrentAsServer {

	private static CurrentAsServer INSTANCE = null;
	
	private AsServer asServer;
	
	public AsServer getAsServer(){
		return asServer;
	}
	
	public static CurrentAsServer getInstance() throws IOException{
		if(INSTANCE==null){
			INSTANCE = new CurrentAsServer();
			String currentServer = Properties.getInstance().getProperty(PropertiesKeys.APPLICATION_SERVER);
			INSTANCE.asServer = AsServer.valueOf(currentServer.toUpperCase());
		}
		return INSTANCE;
	}
	
}
