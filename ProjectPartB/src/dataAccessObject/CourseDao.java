package dataAccessObject;

import entities.Course;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

public class CourseDao extends SuperDao implements InterfaceDao<Course> {

    private final String FINDALL = "SELECT * FROM course";
    private static final String FINDCOURSEBYID = "SELECT * FROM course WHERE cid = ?";
    private static final String INSERTCOURSE = "INSERT INTO course (ctitle, cstream, ctype, sdate, edate) VALUES (?, ?, ?, ?, ?)";


    @Override
    public boolean create(Course c) {
        PreparedStatement ps = null;
        boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTCOURSE);
            ps.setString(1, c.getCtitle());
            ps.setString(2, c.getCstream());
            ps.setString(3, c.getCtype());
            ps.setObject(4, c.getSdate());
            ps.setObject(5, c.getEdate());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps);
        }
        return created;
    }

    @Override
    public List<Course> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList();
        try {
            ps = getConn().prepareStatement(FINDALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String stream = rs.getString(3);
                String type = rs.getString(4);
                LocalDate sdate = rs.getDate(5).toLocalDate();
                LocalDate edate = rs.getDate(6).toLocalDate();
                Course c = new Course(id, title, stream, type, sdate, edate);
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return courses;
    }

    @Override
    public Course findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course c = null;
        try {
            ps = getConn().prepareStatement(FINDCOURSEBYID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            int cid = rs.getInt(1);
            String title = rs.getString(2);
            String stream = rs.getString(3);
            String type = rs.getString(4);
            LocalDate sdate = rs.getDate(5).toLocalDate();
            LocalDate edate = rs.getDate(6).toLocalDate();
            c = new Course(cid, title, stream, type, sdate, edate);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return c;
    }

}
