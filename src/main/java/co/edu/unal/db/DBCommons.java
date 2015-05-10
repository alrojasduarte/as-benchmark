/**
 * 
 */
package co.edu.unal.db;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import co.edu.unal.common.AsServer;
import co.edu.unal.common.Constants;
import co.edu.unal.common.CurrentAsServer;

/**
 * @author Andres
 *
 */
public class DBCommons {

	private static final Logger LOGGER = Logger.getLogger(DBCommons.class);

	private static String JNDI = null;

	public Connection getConnection() throws Exception {
		setJndi();
		InitialContext cxt = new InitialContext();
		DataSource dataSource = (DataSource) cxt.lookup(JNDI);
		if (dataSource == null) {
			throw new Exception("Data source not found!");
		}
		return dataSource.getConnection();
	}

	private void setJndi() throws IOException {
		if (JNDI == null) {
			if (AsServer.JETTY.equals(CurrentAsServer.getInstance()
					.getAsServer())||AsServer.GLASSFISH.equals(CurrentAsServer.getInstance()
							.getAsServer())) {
				JNDI = Constants.JNDI_LOOKUP_NAME_JDBC + "/" + Constants.DATASOURCE_NAME;
			}else if (AsServer.JBOSS.equals(CurrentAsServer.getInstance()
					.getAsServer())) {
				JNDI = Constants.JNDI_LOOKUP_NAME_JAVA + "/" + Constants.DATASOURCE_NAME;
			}else {
				JNDI = Constants.JNDI_LOOKUP_NAME_COMP_ENV + "/" + Constants.JNDI_LOOKUP_NAME_JDBC + "/" + Constants.DATASOURCE_NAME;
			}
		}
		LOGGER.info("Constants.JNDI_DATABASE=" + JNDI);
	}

	public void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
