/**
 * 
 */
package co.edu.unal.ws;

import java.sql.Connection;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import co.edu.unal.db.DBCommons;

/**
 * @author Andrés Leonardo Rojas Duarte - alrojasd@unal.edu.co
 *
 */
@WebService(endpointInterface="co.edu.unal.ws.TestWS")
public class TestWSImpl implements TestWS {

	private static final Logger LOGGER = Logger.getLogger(TestWSImpl.class);
	
	public String hellowWolrd(String name) {
		LOGGER.info("Hellow world was called");
		DBCommons dbCommons = new DBCommons();
		Connection connection = null;
		try {
			connection = dbCommons.getConnection();
			name = "Test ok";
		} catch (Exception e) {
			e.printStackTrace();
			name = "Test Failed";
		}finally{
			dbCommons.close(connection);
		}
	
		return "Hellow "+name;
	}

}
