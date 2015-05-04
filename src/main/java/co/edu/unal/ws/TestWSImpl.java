/**
 * 
 */
package co.edu.unal.ws;

import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * @author Andrés Leonardo Rojas Duarte - alrojasd@unal.edu.co
 *
 */
@WebService(endpointInterface="co.edu.unal.ws.TestWS")
public class TestWSImpl implements TestWS {

	private static final Logger LOGGER = Logger.getLogger(TestWSImpl.class);
	
	public String hellowWolrd(String name) {
		LOGGER.info("Hellow world was called");
		return "Hellow "+name;
	}

}
