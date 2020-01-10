#5a students
select sid, sfname, slname, sdob, tfees from student;

#5b trainers
select tid, tfname, tlname, tsubject from trainer;

#5c assignments
select title, descr, subdate, ormark, tmark from assignment;

#5d courses
select cid, ctitle, cstream, ctype, sdate, edate from course;

#5e students per course
select slname, sfname, sdob, tfees from student s, course c, studentcourse sc
where s.sid = sc.sid and c.cid = sc.cid and c.ctitle = 'cd9' and c.cstream = 'java' and c.ctype = 'full-time'; -- (or: where c.cid = 1)

#5f trainers per course
select tlname, tfname, tsubject from trainer t, course c, trainercourse tc
where t.tid = tc.tid and c.cid = tc.cid and ctitle = 'cd9' and cstream = 'c#' and ctype = 'part-time';

#5g assignments per course
select a.title, descr, subdate, ormark, tmark from assignment a, course c, assignmentcourse ac
where a.aid = ac.aid and c.cid = ac.cid and ctitle = 'cd9' and cstream = 'java' and ctype = 'full-time';

#5h assignments per course per student  
select a.title, a.descr, a.subdate, a.ormark as max_ormark, a.tmark as max_tmark, g.ormark, g.tmark, ctitle, cstream, ctype, sdate, edate from grades g, assignment a, student s, course c
where g.aid = a.aid and g.sid = s.sid and g.cid = c.cid
and s.sid = 1  
order by ctitle, cstream, ctype;

#5i students that belong to more than one courses
select s.sid, s.slname, s.sfname, sdob, tfees, count(*) as 'number of courses' from  student s, studentcourse sc
where s.sid = sc.sid 
group by s.sid
having count(*) > 1;