/**
 * 
 */
package co.edu.unal.ws;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import co.edu.unal.common.CurrentAsServer;
import co.edu.unal.common.Properties;
import co.edu.unal.common.PropertiesKeys;
import co.edu.unal.common.RandomDataGenerator;
import co.edu.unal.common.RandomDataGeneratorConstants;
import co.edu.unal.common.SqlQueries;
import co.edu.unal.common.TestType;
import co.edu.unal.db.DBCommons;

/**
 * @author Andrés Leonardo Rojas Duarte - alrojasd@unal.edu.co
 *
 */
@SuppressWarnings("restriction")
@WebService(endpointInterface="co.edu.unal.ws.PersistTestWS")
public class PersistTestWSImpl implements PersistTestWS {

	private static final Logger LOGGER = Logger.getLogger(PersistTestWSImpl.class);


	public void doTest() {
		LOGGER.info("PersistTestWS started");
		DBCommons dbCommons = new DBCommons();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbCommons.getConnection();
			Date startDate = new Date();
			int rowsCount = persistRecords(connection,dbCommons);
			Date endDate = new Date();
			long elapsedTime = endDate.getTime()-startDate.getTime();
			persistBenchMarkResult(startDate,endDate,elapsedTime,rowsCount,connection,dbCommons);
		} catch (Exception e) {
			e.printStackTrace();
			((SQLException)e).getNextException().printStackTrace();
		}finally{
			dbCommons.close(connection);
			dbCommons.close(preparedStatement);
		}
		LOGGER.info("PersistTestWS finished");
	}


	private void persistBenchMarkResult(Date startDate, Date endDate,
			long elapsedTime,int rowsCount, Connection connection, DBCommons dbCommons) throws SQLException, IOException {
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_AS_BENCHMARK);
		try{
			preparedStatement.setString(1,CurrentAsServer.getInstance().getAsServer().toString());
			preparedStatement.setString(2,TestType.PERSIST_ROWS.toString());
			preparedStatement.setTimestamp(3, new java.sql.Timestamp(startDate.getTime()));
			preparedStatement.setTimestamp(4, new java.sql.Timestamp(endDate.getTime()));
			preparedStatement.setLong(5,elapsedTime);
			preparedStatement.setInt(6, rowsCount);
			preparedStatement.execute();
		}finally{
			dbCommons.close(preparedStatement);
		}
	}


	private int persistRecords(Connection connection,DBCommons dbCommons) throws IOException, SQLException {
		int rowsCount = 0;
		int recordsToPersist = Properties.getInstance().getPropertyAsInteger(PropertiesKeys.RECORDS_TO_PERSIST);
		int recordsBatchSize = Properties.getInstance().getPropertyAsInteger(PropertiesKeys.RECORDS_BATCH_SIZE);
		RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
		PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.INSERT_PERSON);
		try{
			for(int i = 1;i<=recordsToPersist;i++){
				String firstName = randomDataGenerator.getString(RandomDataGeneratorConstants.PERSON_FIRST_NAME_MIN_LENGTH, RandomDataGeneratorConstants.PERSON_FIRST_NAME_MAX_LENGTH);
				String secondName = randomDataGenerator.getString(RandomDataGeneratorConstants.PERSON_SECOND_NAME_MIN_LENGTH, RandomDataGeneratorConstants.PERSON_SECOND_NAME_MAX_LENGTH);
				String firstLastName = randomDataGenerator.getString(RandomDataGeneratorConstants.PERSON_FIRST_LASTNAME_MIN_LENGTH, RandomDataGeneratorConstants.PERSON_FIRST_LASTNAME_MAX_LENGTH);
				String secondLastName = randomDataGenerator.getString(RandomDataGeneratorConstants.PERSON_SECOND_LASTNAME_MIN_LENGTH, RandomDataGeneratorConstants.PERSON_SECOND_LASTNAME_MAX_LENGTH);
				
				int personAge = randomDataGenerator.getInteger(RandomDataGeneratorConstants.PERSON_MIN_AGE, RandomDataGeneratorConstants.PERSON_MAX_AGE);
				Calendar birthDateCalendar = Calendar.getInstance();
				birthDateCalendar.add(Calendar.YEAR, -personAge);
				Date birthDate = birthDateCalendar.getTime();
				
				char gender = randomDataGenerator.getRandomChar(RandomDataGeneratorConstants.PERSON_GENDER);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, secondName);
				preparedStatement.setString(3, firstLastName);
				preparedStatement.setString(4, secondLastName);
				preparedStatement.setDate(5, new java.sql.Date(birthDate.getTime()));
				preparedStatement.setString(6, String.valueOf(gender));
				
				preparedStatement.addBatch();
				if(i%recordsBatchSize==0){
					rowsCount += dbCommons.executeBatch(preparedStatement);					
				}
			}
			
			rowsCount += dbCommons.executeBatch(preparedStatement);
		}finally{
			dbCommons.close(preparedStatement);
		}
		return rowsCount;
	}

}
