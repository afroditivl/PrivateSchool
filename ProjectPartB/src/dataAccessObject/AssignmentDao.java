package dataAccessObject;

import entities.Assignment;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.*;

public class AssignmentDao extends SuperDao implements InterfaceDao<Assignment> {

    private final String FINDALL = "SELECT * FROM assignment";
    private static final String FINDASSIGNMENTBYID = "SELECT * FROM assignment WHERE aid = ?";
    private static final String INSERTASSIGNMENT = "INSERT INTO assignment (title, descr, subdate, ormark, tmark) VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean create(Assignment a) {
        PreparedStatement ps = null;
        Boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTASSIGNMENT);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getDescription());
            ps.setObject(3, a.getSubDate());
            ps.setDouble(4, a.getOralMark());
            ps.setDouble(5, a.getTotalMark());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps);
        }
        return created;
    }

    @Override
    public List<Assignment> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Assignment> assignments = new ArrayList();
        try {
            ps = getConn().prepareStatement(FINDALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                LocalDate subDate = rs.getDate(4).toLocalDate();
                int oralMark = rs.getInt(5);
                int totalMark = rs.getInt(6);
                Assignment a = new Assignment(id, title, description, subDate, oralMark, totalMark);
                assignments.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return assignments;
    }

    @Override
    public Assignment findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Assignment a = null;
        try {
            ps = getConn().prepareStatement(FINDASSIGNMENTBYID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            int aid = rs.getInt(1);
            String title = rs.getString(2);
            String description = rs.getString(3);
            LocalDate subDate = rs.getDate(4).toLocalDate();
            int oralMark = rs.getInt(5);
            int totalMark = rs.getInt(6);
            a = new Assignment(aid, title, description, subDate, oralMark, totalMark);
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return a;
    }
}
