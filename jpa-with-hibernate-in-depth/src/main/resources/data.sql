insert into course(id, name, last_updated_date_time, created_date_time) values(10001L, 'History', null, sysdate());
insert into course(id, name, last_updated_date_time, created_date_time) values(10002L, 'English', null, sysdate());

insert into passport(id, number) values(40001L, 'A1234567');
insert into passport(id, number) values(40002L, 'B7654321');
insert into passport(id, number) values(40003L, 'C5678900');

insert into student(id, fullname, passport_id) values(20001L, 'Jarrod Kallis', 40001);
insert into student(id, fullname, passport_id) values(20002L, 'Tessa Kallis', 40002);
insert into student(id, fullname) values(20003L, 'Ben Kallis');

insert into review(id, rating, description) values(50001, '5', 'Great Course');
insert into review(id, rating, description) values(50002, '1', 'You suck!');
