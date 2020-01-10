package dataAccessObject;

import entities.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class TrainerCourseDao extends SuperDao {

    private static final String INSERTTRAINERCOURSE = "INSERT INTO trainercourse (tid, cid) VALUES (?, ?)";
    private static final String FINDTRAINERSPERCOURSE = "select t.tid, t.tfname, t.tlname, t.tsubject from trainer t, course c, trainercourse tc\n" +
                                                        "where t.tid = tc.tid and c.cid = tc.cid and c.cid = ?";

    
    public boolean create(TrainerCourse tc) {
        PreparedStatement ps = null;
        Boolean created = false;
        try {
            ps = getConn().prepareStatement(INSERTTRAINERCOURSE);
            ps.setObject(1, tc.getTrainer().getTid());
            ps.setObject(2, tc.getCourse().getCid());
            int result = ps.executeUpdate();
            if (result > 0) {
                created = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(ps);
        }
        return created;
    }

    public List<Trainer> findTrByCourse(int id){
        List<Trainer> list = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getConn().prepareStatement(FINDTRAINERSPERCOURSE);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                int tid = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String subject = rs.getString(4);
                Trainer t = new Trainer(tid, fname, lname, subject);
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnections(rs, ps);
        }
        return list;
    }

}
