package co.edu.unal.common;

public interface SqlQueries {
	String INSERT_AS_BENCHMARK=" INSERT INTO as_benchmark(as_server, test_type, start_time, end_time, elapsed_time,rows_count) VALUES (?, ?, ?, ?, ?, ?) ";
	String INSERT_PERSON =" INSERT INTO person(first_name, second_name, first_lastname, second_lastname,birth_date, gender) VALUES (?, ?, ?, ?, ?, ?) ";
}