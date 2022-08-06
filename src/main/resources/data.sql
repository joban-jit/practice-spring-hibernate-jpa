insert into course_details(id, fullname, created_date, last_updated_date)
values(10001, 'JPA in 50 Steps', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into course_details(id, fullname, created_date, last_updated_date)
values(10002, 'PySpark for Beginner', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into course_details(id, fullname, created_date, last_updated_date)
values(10003, 'AWS Data Specialist',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());


insert into passport(id, number)
values(40001, 'E123456');
insert into passport(id, number)
values(40002, 'N123457');
insert into passport(id, number)
values(40003, 'L123890');


insert into student(id, name, passport_id)
values(20001, 'John', 40001);
insert into student(id, name, passport_id)
values(20002, 'Marcus', 40002);
insert into student(id, name, passport_id)
values(20003, 'Nina', 40003);


insert into review(id, rating, description)
values(50001, '5', 'Great Course');
insert into review(id, rating, description)
values(50002, '4', 'Wonderful Course');
insert into review(id, rating, description)
values(50003, '5', 'Awesome Course');