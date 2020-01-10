package dataAccessObject;

import entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

public class StudentCourseDao extends SuperDao {

    private static final String INSERTSTUDENTCOURSE = "INSERT INTO studentcourse (sid, cid) VALUES (?, ?)";
    private static final String FINDSTUDENTSPERCOURSE = "SELECT s.sid, s.sfname, s.slname, s.sdob, s.tfees FROM student s, course c,"
                                                        + " studentcourse sc where s.sid = sc.sid and c.cid = sc.cid and sc.cid = ?";
    private static final String FINDCOURSESPERSTUDENT = "SELECT c.cid, c.ctitle, c.cstream, c.ctype, c.sdate, c.edate FROM student s, course c,"
                                                        + " studentcourse sc where s.sid = sc.sid and c.cid = sc.cid and sc.sid = ?";
    private static final String FINDSTMANYCOURSES = "select s.sid, s.slname, s.sfname, sdob, tfees, count(*) as 'number of courses' from  student s, studentcourse sc\n"
                                                    + "where s.sid = sc.sid group by s.sid having count(*) > 1";

    public boolean create(StudentCourse sc) {
        PreparedStatement ps = null;
        Boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTSTUDENTCOURSE);
            ps.setObject(1, sc.getStudent().getSid());
            ps.setObject(2, sc.getCourse().getCid());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps);
        }
        return created;
    }

    public List<Student> findStByCourse(int id) {
        List<Student> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDSTUDENTSPERCOURSE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                double tfees = rs.getDouble(5);
                Student s = new Student(sid, fname, lname, dob, tfees);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return list;
    }

    public List<Course> findCoursesByStudent(int id) {
        List<Course> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDCOURSESPERSTUDENT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDate sdate = rs.getDate(5).toLocalDate();
                LocalDate edate = rs.getDate(6).toLocalDate();
                Course c = new Course(cid, title, stream, type, sdate, edate);
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return list;
    }

    public List<Student> StudentsWithManyCourses() {
        List<Student> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDSTMANYCOURSES);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                double tfees = rs.getDouble(5);
                Student s = new Student(id, fname, lname, dob, tfees);
                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return list;
    }
}
