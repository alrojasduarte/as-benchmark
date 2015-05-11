CREATE SEQUENCE seq_as_benchmark
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_as_benchmark
  OWNER TO as_benchmark;


CREATE TABLE as_benchmark
(
  id bigint NOT NULL DEFAULT nextval('seq_as_benchmark'::regclass), -- The table primary key
  as_server character(50), -- Stores the application server name
  test_type character(50), -- Stores the test type
  start_time timestamp with time zone,  -- Stores the start time of the test
  end_time timestamp with time zone, -- Stores the end time of the test
  elapsed_time integer, -- The test elapsed time
  rows_count integer, -- The processed rows count during the test
  CONSTRAINT as_benchmark_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE as_benchmark
  OWNER TO as_benchmark;
COMMENT ON COLUMN as_benchmark.id IS 'The table primary key';
COMMENT ON COLUMN as_benchmark.as_server IS 'Stores the application server name';
COMMENT ON COLUMN as_benchmark.test_type IS 'Stores the test type';
COMMENT ON COLUMN as_benchmark.start_time IS 'Stores the start time of the test';
COMMENT ON COLUMN as_benchmark.end_time IS 'Stores the end time of the test';
COMMENT ON COLUMN as_benchmark.rows_count IS 'The processed rows count during the test';
COMMENT ON COLUMN as_benchmark.elapsed_time IS 'The test elapsed time';

