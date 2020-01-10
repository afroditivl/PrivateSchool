CREATE DATABASE IF NOT EXISTS privateSchool; 
use privateSchool;

create table student(
sid int unsigned auto_increment primary key,
sfname varchar(20),
slname varchar(20),
sdob date,
tfees decimal(6,1) unsigned default 0
);

create table course(
cid int unsigned auto_increment primary key,
ctitle varchar(20),
cstream varchar(20),
ctype varchar(10),
sdate date,
edate date
);

create table assignment(
aid int auto_increment primary key,
title varchar(20),
descr varchar(20),
subdate date,
ormark decimal (4,1) unsigned default 0,
tmark decimal (4,1) unsigned default 0
);

create table trainer(
tid int unsigned auto_increment primary key,
tfname varchar(20),
tlname varchar(20),
tsubject varchar(20)
);

create table studentcourse(
scid int unsigned auto_increment primary key,
sid int unsigned not null,
cid int unsigned not null,
constraint fk_student foreign key (sid)
references student (sid),
constraint fk_course foreign key (cid)
references course (cid)
);

create table trainercourse(
tcid int unsigned auto_increment primary key,
tid int unsigned not null,
cid int unsigned not null,
constraint fk_trainer foreign key (tid)
references trainer (tid),
constraint fk_trcourse foreign key (cid)
references course (cid)
);

create table assignmentcourse(
acid int unsigned auto_increment primary key,
aid int not null,
cid int unsigned not null,
constraint fk_assignment foreign key (aid)
references assignment (aid),
constraint fk_asscourse foreign key (cid)
references course (cid)
);

-- if marks are 0 --> assignment has not been assigned or corrected yet.
create table grades(
gid int unsigned auto_increment primary key,
sid int unsigned not null,
cid int unsigned not null,
aid int not null,
ormark decimal (4,1) unsigned,
tmark decimal (4,1) unsigned,
constraint fk_assignments foreign key (aid)
references assignment (aid),
constraint fk_courses foreign key (cid)
references course (cid),
constraint fk_students foreign key (sid)
references student (sid));

insert into student(sid, sfname, slname, sdob, tfees)
values (1, 'Ioannis', 'Antoniadis', '1994-12-26', 5000),
(2, 'Stella', 'Mpranti', '1995-05-05', 2150),
(3, 'Eleftheria', 'Themeli', '1996-05-19', 2250),
(4, 'Thodoris', 'Mouratidis', '1994-05-08', 4800),
(5, 'Ioannis', 'Grigoriou', '1995-05-08', 4750),
(6, 'George', 'Dermentzis', '1995-11-06', 5000),
(7, 'Aggelos', 'Alexopoulos', '1995-01-30', 2500),
(8, 'Athanasios', 'Labropoulos', '1995-05-14', 2250),
(9, 'Victor', 'Timofei', '1994-09-26', 5000),
(10, 'Anastasia', 'Misichroni', '1987-05-30', 2400);

insert into trainer(tid, tfname, tlname, tsubject)
values (1, 'Miltos', 'Pasxalidis', 'java'),
(2, 'George', 'Sampanis', 'c#'),
(3, 'George', 'Vagiatas', 'sql'),
(4, 'Pantelis', 'Thalassinos', 'web-applications'),
(5, 'Christos', 'Thivaios', 'java');

insert into course
values (1, 'cd9', 'java', 'full-time', '2019-10-14', '2020-01-22'), 
(2, 'cd9', 'java', 'part-time', '2019-10-14', '2020-04-23'),
(3, 'cd9', 'c#', 'full-time', '2019-10-14', '2020-01-22'),
(4, 'cd9', 'c#', 'part-time', '2019-10-14', '2020-04-23');

insert into assignment
values (1, 'individual1', 'private-school', '2019-11-04', 45, 100),
(2, 'individual2', 'private-school', '2019-11-18', 40, 100),
(3, 'team', 'web-application', '2020-01-20', 0, 100),
(4, 'individual', 'java/sql', '2020-04-21', 55, 100);

insert into studentcourse
values (1, 1, 1),
(2, 1, 4),
(3, 2, 3),
(4, 3, 1),
(5, 4, 2),
(6, 4, 3),
(7, 5, 2),
(8, 5, 3),
(9, 6, 3),
(10, 6, 1),
(11, 7, 3),
(12, 8, 1),
(13, 9, 1),
(14, 9, 4),
(15, 10, 4);

insert into trainercourse
values (1, 1, 1),
(2, 2, 3),
(3, 2, 4),
(4, 3, 1),
(5, 3, 3),
(6, 4, 2),
(7, 4, 4),
(8, 5, 2);

insert into assignmentcourse
values (1, 4, 2),
(2, 1, 1),
(3, 1, 3),
(4, 2, 1),
(5, 2, 3),
(6, 3, 1),
(7, 3, 3),
(8, 3, 4);

insert into grades (sid, cid, aid)
select sc.sid, sc.cid, a.aid from student s, course c, assignment a, studentcourse sc, assignmentcourse ac
where s.sid = sc.sid and c.cid = sc.cid and a.aid = ac.aid and c.cid = ac.cid order by sid;

--  INSERT students' grades for assignments that have already assigned and checked
update grades 
set ormark = 20 where gid = 1;
update grades 
set tmark = 54 where gid = 1;

update grades 
set ormark = 21 where gid = 3;
update grades 
set tmark = 64 where gid = 3;

update grades 
set ormark = 35 where gid = 6;
update grades 
set tmark = 77 where gid = 6;

update grades 
set ormark = 39 where gid = 8;
update grades 
set tmark = 93 where gid = 8;

update grades 
set ormark = 20 where gid = 9;
update grades 
set tmark = 64 where gid = 9;

update grades 
set ormark = 15 where gid = 10;
update grades 
set tmark = 32 where gid = 10;

update grades 
set ormark = 40 where gid = 13;
update grades 
set tmark = 95 where gid = 13;

update grades 
set ormark = 22 where gid = 15;
update grades 
set tmark = 49 where gid = 15;

update grades 
set ormark = 19 where gid = 16;
update grades 
set tmark = 41 where gid = 16;

update grades 
set ormark = 38 where gid = 17;
update grades 
set tmark = 82 where gid = 17;

update grades 
set ormark = 37 where gid = 20;
update grades 
set tmark = 89 where gid = 20;

update grades 
set ormark = 20 where gid = 21;
update grades 
set tmark = 45 where gid = 21;

update grades 
set ormark = 31 where gid = 23;
update grades 
set tmark = 64 where gid = 23;

update grades 
set ormark = 18 where gid = 24;
update grades 
set tmark = 36 where gid = 24;

update grades 
set ormark = 34 where gid = 26;
update grades 
set tmark = 60 where gid = 26;

update grades 
set ormark = 20 where gid = 27;
update grades 
set tmark = 33 where gid = 27;

update grades 
set ormark = 40 where gid = 29;
update grades 
set tmark = 94 where gid = 29;

update grades 
set ormark = 26 where gid = 30;
update grades 
set tmark = 57 where gid = 30;

update grades 
set ormark = 15 where gid = 32;
update grades 
set tmark = 36 where gid = 32;

update grades 
set ormark = 25 where gid = 33;
update grades 
set tmark = 43 where gid = 33;