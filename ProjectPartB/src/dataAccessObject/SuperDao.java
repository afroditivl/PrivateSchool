package dataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperDao {
    
    public final String URL = "jdbc:mysql://localhost:3306/privateschool?serverTimezone=UTC";
    public final String USER = "root";
    public final String PASS = "1234";
    private Connection conn;
    
    public Connection getConn() { 
        openConnection();
        return conn;
    }
    
    protected void openConnection(){
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            Logger.getLogger(SuperDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void closeConnections(ResultSet rs, Statement stmt){
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuperDao.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NullPointerException e){
                e.printStackTrace();
        }
    }
    
    protected void closeConnections(Statement st){
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuperDao.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException e){
                e.printStackTrace();            
        }
    }
}
