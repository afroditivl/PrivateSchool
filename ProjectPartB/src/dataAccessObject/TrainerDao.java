package dataAccessObject;

import entities.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class TrainerDao extends SuperDao implements InterfaceDao<Trainer> {

    private final String FINDALL = "SELECT * FROM trainer";
    private static final String FINDTRAINERBYID = "SELECT * FROM trainer WHERE tid = ?";
    private static final String INSERTTRAINER = "INSERT INTO trainer (tfname, tlname, tsubject) VALUES (?, ?, ?)";

    @Override
    public boolean create(Trainer t) {
        PreparedStatement pst = null;
        Boolean created = false;
        try {
            pst = getConn().prepareStatement(INSERTTRAINER);
            pst.setString(1, t.getTfname());
            pst.setString(2, t.getTlname());
            pst.setString(3, t.getTsubject());
            int result = pst.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pst);
        }
        return created;
    }

    @Override
    public List<Trainer> findAll() {
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Trainer> trainers = new ArrayList();
        try {
            ps = getConn().prepareStatement(FINDALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer t = new Trainer(id, fname, lname, subject);
                trainers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return trainers;
    }

    @Override
    public Trainer findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Trainer t = null;
        try {
            ps = getConn().prepareStatement(FINDTRAINERBYID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            int tid = rs.getInt(1);
            String fname = rs.getString(2);
            String lname = rs.getString(3);
            String subject = rs.getString(4);
            t = new Trainer(id, fname, lname, subject);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, ps);
        }
        return t;
    }
}
