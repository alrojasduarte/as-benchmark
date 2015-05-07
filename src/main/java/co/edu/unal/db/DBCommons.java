/**
 * 
 */
package co.edu.unal.db;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import co.edu.unal.common.AsServer;
import co.edu.unal.common.Constants;
import co.edu.unal.common.CurrentAsServer;

/**
 * @author Andres
 *
 */
public class DBCommons {

	private static String JNDI = null;
	
	public Connection getConnection() throws Exception{
		InitialContext cxt = new InitialContext();
		if(JNDI==null){
			if(AsServer.JETTY.equals(CurrentAsServer.getInstance().getAsServer())){
				JNDI = Constants.JNDI_LOOKUP_NAME_DATASOURE_JETTY+Constants.DATASOURCE_NAME;
			}			
		}
		System.out.println("Constants.JNDI_DATABASE="+JNDI);
		DataSource dataSource = (DataSource) cxt.lookup(JNDI);

		if ( dataSource == null ) {
		   throw new Exception("No datasource found");
		}
		return dataSource.getConnection();
	}
	
	
	public void close(Connection connection){
		try{
			if(connection!=null){
				connection.close();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
}
