create table person
(
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  dob timestamp,
  primary key(id)
);

insert into person (id, name, location, dob)
values (10001, 'Jarrod', 'Home', sysdate());

insert into person (id, name, location, dob)
values (10002, 'Tessa', 'Work', sysdate());

insert into person (id, name, location, dob)
values (10003, 'Ben', 'School', sysdate());

insert into person (id, name, location, dob)
values (10004, 'Jarrod Kallis', 'Ireland', sysdate());
