-- inserting data into
-- course details
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10001, 'JPA in 50 Steps', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10002, 'PySpark for Beginner', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10003, 'AWS Data Specialist',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10004, 'Dummy0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10005, 'Dummy1', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10006, 'Dummy2',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10007, 'Dummy3', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);
insert into course_details(id, fullname, created_date, last_updated_date, is_deleted)
values(10008, 'Dummy4',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), false);

-- passport table
insert into passport(id, number)
values(40001, 'E123456');
insert into passport(id, number)
values(40002, 'N123457');
insert into passport(id, number)
values(40003, 'L123890');

-- student table
insert into student(id, name, passport_id, line1, line2, city)
values(20001, 'John', 40001, 'House 101', 'Street 102','New York');
insert into student(id, name, passport_id, line1, line2, city)
values(20002, 'Marcus', 40002, 'House 201', 'Street 202','Gurugram');
insert into student(id, name, passport_id, line1, line2, city)
values(20003, 'Nina', 40003, 'House 301', 'Street 302','London');

-- review table
insert into review(id, rating, description, course_id)
values(50001, 'FIVE', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values(50002, 'FOUR', 'Wonderful Course', 10002);
insert into review(id, rating, description, course_id)
values(50003, 'FIVE', 'Awesome Course', 10001);

-- student_course table
insert into student_course(student_id, course_id)
values(20001,10001);
insert into student_course(student_id, course_id)
values(20002,10001);
insert into student_course(student_id, course_id)
values(20003,10001);
insert into student_course(student_id, course_id)
values(20001,10003);
