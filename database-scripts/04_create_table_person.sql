CREATE SEQUENCE seq_person
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_person
  OWNER TO as_benchmark;
  
  
CREATE TABLE person
(
  id integer DEFAULT nextval('seq_person'::regclass), -- The table primary key
  first_name character(50), -- The first name of the person
  second_name character(50), -- The second name of the person
  first_lastname character(50), -- The first lastname of the person
  second_lastname character(50), -- The second last name of the person
  birth_date date, -- The person birth date
  gender "char" -- The gender of the person
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person
  OWNER TO as_benchmark;
COMMENT ON TABLE person
  IS 'Stores the persons generated info';
COMMENT ON COLUMN person.id IS 'The table primary key';
COMMENT ON COLUMN person.first_name IS 'The first name of the person';
COMMENT ON COLUMN person.second_name IS 'The second name of the person';
COMMENT ON COLUMN person.first_lastname IS 'The first lastname of the person';
COMMENT ON COLUMN person.second_lastname IS 'The second last name of the person';
COMMENT ON COLUMN person.birth_date IS 'The person birth date';
COMMENT ON COLUMN person.gender IS 'The gender of the person';

