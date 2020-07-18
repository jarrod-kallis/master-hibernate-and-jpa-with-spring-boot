-- This gets called because of auto configuration, but it must be called data.sql

-- This needs to be commented out once the JPA Person class gets the @Entity annotation assigned to it.
-- Because this will cause the Person table to be created automatically. 
/*
create table person
(
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  dob timestamp,
  primary key(id)
);
*/

insert into person (id, name, location, dob)
values (10001, 'Jarrod', 'Home', sysdate() - 10000);

insert into person (id, name, location, dob)
values (10002, 'Tessa', 'Work', sysdate() - 15000);

insert into person (id, name, location, dob)
values (10003, 'Ben', 'School', sysdate() - 5000);

insert into person (id, name, location, dob)
values (10004, 'Jarrod Kallis', 'Ireland', sysdate() - 20000);
