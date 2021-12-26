/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.dao;

import app.quiz.tech.dbutil.DBConnection;
import app.quiz.tech.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sambhavj
 */
public class UserDAO {
    private static Connection conn;
    private static PreparedStatement ps;
    public static boolean validateUser(User u) throws SQLException
    {
        
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("Select * from users where userid=? and password=? and usertype=?");
        ps.setString(1, u.getUserId());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getUserType());
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    public static boolean addUser(User u) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("insert into users values(?,?,?)");
        ps.setString(1, u.getUserId());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getUserType());
        int res=ps.executeUpdate();
        if(res==1)
        {
            return true;
        }
        return false;
    }
    public static boolean updateUserPassword(User u) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("update users set password=? where userid=? and usertype=?");
        ps.setString(1, u.getPassword());
        ps.setString(2, u.getUserId());
        ps.setString(3, u.getUserType());
        int res=ps.executeUpdate();
        if(res==1)
        {
            return true;
        }
        return false;
    }
}
