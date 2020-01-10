package dataAccessObject;

import entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

public class AssignmentCourseDao extends SuperDao{

    private static final String INSERTASSIGNMENTCOURSE = "INSERT INTO assignmentcourse (aid, cid) VALUES (?, ?)";
    private static final String FINDASSIGNMENTSPERCOURSE = "select a.aid, a.title, a.descr, a.subdate, a.ormark, a.tmark from assignment a, course c, "
                                                         + "assignmentcourse ac where a.aid = ac.aid and c.cid = ac.cid and ac.cid = ?";

    
    public boolean create(AssignmentCourse ac) {
        PreparedStatement ps = null;
        Boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTASSIGNMENTCOURSE);
            ps.setObject(1, ac.getAssignment().getAid());
            ps.setObject(2, ac.getCourse().getCid());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps);
        }
        return created;
    }

    public List<Assignment> findAsByCourse(int id){
        List<Assignment> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDASSIGNMENTSPERCOURSE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                int aid = rs.getInt(1);
                String title = rs.getString(2);
                String descr = rs.getString(3);
                LocalDate sdate = rs.getDate(4).toLocalDate();
                double ormark = rs.getDouble(5);
                double tmark = rs.getDouble(6);
                Assignment a = new Assignment(aid, title, descr, sdate, ormark, tmark);
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return list;
    }
}
