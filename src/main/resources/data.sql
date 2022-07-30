--create table person
--(
--    id integer not null,
--    name varchar(255) not null,
--    location varchar(255),
--    birth_date timestamp,
--    primary key (id)
--);

INSERT INTO person (id, name, location, birth_date)
VALUES(10001, 'Joban', 'Gurgaon', CURRENT_TIMESTAMP());

INSERT INTO person (id, name, location, birth_date)
VALUES(10002, 'Harry', 'London', CURRENT_TIMESTAMP() );

INSERT INTO person (id, name, location, birth_date)
VALUES(10003, 'Hermoine', 'London', CURRENT_TIMESTAMP() );
