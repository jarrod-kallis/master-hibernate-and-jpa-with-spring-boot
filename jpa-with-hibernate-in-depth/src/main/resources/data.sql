insert into course(id, name, last_updated_date_time, created_date_time) values(10001L, 'History', null, sysdate());
insert into course(id, name, last_updated_date_time, created_date_time) values(10002L, 'English', null, sysdate());
insert into course(id, name, last_updated_date_time, created_date_time) values(10003L, 'ReactJS', null, sysdate());
insert into course(id, name, last_updated_date_time, created_date_time) values(10004L, 'Temp Course', null, sysdate());

insert into passport(id, number) values(40001L, 'A1234567');
insert into passport(id, number) values(40002L, 'B7654321');
insert into passport(id, number) values(40003L, 'C5678900');

insert into student(id, fullname, passport_id) values(20001L, 'Jarrod Kallis', 40001);
insert into student(id, fullname, passport_id) values(20002L, 'Tessa Kallis', 40002);
insert into student(id, fullname, passport_id) values(20003L, 'Ben Kallis', 40003);

insert into review(id, rating, description, course_id, student_id) values(50001, '5', 'Great Course', 10001L, 20001L);
insert into review(id, rating, description, course_id, student_id) values(50002, '1', 'You suck!', 10001L, 20001L);
insert into review(id, rating, description, course_id, student_id) values(50003, '4', 'You rock!', 10002L, 20002L);

insert into student_course(student_id, course_id) values(20001L, 10001L);
insert into student_course(student_id, course_id) values(20002L, 10001L);
insert into student_course(student_id, course_id) values(20003L, 10003L);
insert into student_course(student_id, course_id) values(20002L, 10003L);
insert into student_course(student_id, course_id) values(20002L, 10002L);