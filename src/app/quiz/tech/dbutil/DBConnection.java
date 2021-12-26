/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sambhavj
 */
public class DBConnection {
    private static Connection conn;
    static{
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-LN2OA1R:1521/xe", "project", "javaproject");
            JOptionPane.showMessageDialog(null, "Connected to DB successfully", "Success",JOptionPane.INFORMATION_MESSAGE);            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Cannot connect to DB", "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }
     public static Connection getConnection() {
        return conn;
    }
    
     public static void closeConnection(){
         try
         {
             if(conn!=null)
             {
                 conn.close();
                 JOptionPane.showMessageDialog(null, "Disconnected Successfully from DB", "Success", JOptionPane.INFORMATION_MESSAGE);
             }
             
         }catch(SQLException ex)
         {
             JOptionPane.showMessageDialog(null, "Cannot Disconnect from DB", "Error", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
         }
     }
    
    
}
