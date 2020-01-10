package dataAccessObject;

import entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradesDao extends SuperDao{
    
    private static final String FINDASSPERCOURSEPERSTUDENT = "select a.aid, a.title, a.descr, a.subdate, a.ormark, a.tmark, g.ormark, g.tmark, ctitle, cstream, ctype, sdate, edate from grades g, assignment a, student s, course c\n" +
                                                            "where g.aid = a.aid and g.sid = s.sid and g.cid = c.cid and s.sid = ? order by ctitle, cstream, ctype";
    private static final String INSERTGRADES = "INSERT INTO grades (sid, cid, aid, ormark, tmark) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATEGRADESORAL = "update grades set ormark = ? where sid = ? and cid=? and aid=?";
    private static final String UPDATEGRADESTOTAL = "update grades set tmark = ? where sid = ? and cid=? and aid=?";
    private static final String FINDGRADESBYStCAs = "select count(*) from grades where sid=? and cid=? and aid=?";
    
    
    public boolean create(Grade g) {
        PreparedStatement ps = null;
        Boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTGRADES);
            ps.setObject(1, g.getStudent().getSid());
            ps.setObject(2, g.getCourse().getCid());
            ps.setObject(3, g.getAssignment().getAid());
            ps.setDouble(4, g.getOrmark());
            ps.setDouble(5, g.getTmark());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps);
        }
        return created;
    }
        
    public List<Grade> findGradesById(int id){
        List<Grade> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDASSPERCOURSEPERSTUDENT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                int aid = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subdate = rs.getDate(4).toLocalDate();
                double maxormark = rs.getDouble(5);
                double maxtmark = rs.getDouble(6);
                double ormark = rs.getDouble(7);
                double tmark = rs.getDouble(8);
                String ctitle = rs.getString(9);
                String cstream = rs.getString(10);
                String ctype = rs.getString(11);
                LocalDate sdate = rs.getDate(12).toLocalDate();
                LocalDate edate = rs.getDate(13).toLocalDate();
                Assignment a = new Assignment(aid, title, description, subdate, maxormark, maxtmark);
                Course c = new Course(ctitle, cstream, ctype, sdate, edate);
                Grade g = new Grade(c, a, ormark, tmark);
                list.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return list;
    }
    
    public boolean updateGradesOral(int sid, int cid, int aid, double ormark) {
        PreparedStatement ps = null;
        Boolean updated = false;
        try {
            ps = getConn().prepareStatement(UPDATEGRADESORAL);
            ps.setDouble(1, ormark);
            ps.setInt(2, sid);
            ps.setInt(3, cid);
            ps.setInt(4, aid);
            int result = ps.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps);
        }
        return false;
    }
    
    public boolean updateGradesTotal(int sid, int cid, int aid, double tmark) {
        PreparedStatement ps = null;
        Boolean updated = false;
        try {
            ps = getConn().prepareStatement(UPDATEGRADESTOTAL);
            ps.setDouble(1, tmark);
            ps.setInt(2, sid);
            ps.setInt(3, cid);
            ps.setInt(4, aid);
            int result = ps.executeUpdate();
            if (result > 0) {
                updated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps);
        }
        return false;
    }
    
    public int findGrades(int sid, int cid, int aid){
        int a=-1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDGRADESBYStCAs);
            ps.setInt(1, sid);
            ps.setInt(2, cid);
            ps.setInt(3, aid);
            rs=ps.executeQuery();
            rs.next();
            a = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(GradesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            closeConnections(ps);
        }
        return a;
    }
    
    
}
