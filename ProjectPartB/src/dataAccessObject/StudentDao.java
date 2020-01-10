package dataAccessObject;

import entities.Student;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

public class StudentDao extends SuperDao implements InterfaceDao<Student> {

    private final String FINDALL = "SELECT * FROM student";
    private static final String FINDSTUDENTBYID = "SELECT * FROM student WHERE sid = ?";
    private static final String INSERTSTUDENT = "INSERT INTO student (sfname, slname, sdob, tfees) VALUES (?, ?, ?, ?)";

    @Override
    public boolean create(Student s) {
        PreparedStatement pst = null;
        Boolean created = false;
        try {
            pst = getConn().prepareStatement(INSERTSTUDENT);
            pst.setString(1, s.getSfname());
            pst.setString(2, s.getLname());
            pst.setObject(3, s.getSdob());
            pst.setDouble(4, s.getTfees());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return created;
    }

    @Override
    public List<Student> findAll() {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Student> students = new ArrayList();
        try {
            stmt = getConn().prepareStatement(FINDALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                LocalDate dob = rs.getDate(4).toLocalDate();
                double tfees = rs.getDouble(5);
                Student s = new Student(id, fname, lname, dob, tfees);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, stmt);
        }
        return students;
    }

    @Override
    public Student findById(int id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Student s = null;
        try {
            ps = getConn().prepareStatement(FINDSTUDENTBYID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            int sid = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            LocalDate dob = rs.getDate(4).toLocalDate();
            double tfees = rs.getDouble(5);
            s = new Student(sid, fname, lname, dob, tfees);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return s;
    }
}
