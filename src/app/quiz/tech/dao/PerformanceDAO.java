/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.dao;

import app.quiz.tech.dbutil.DBConnection;
import app.quiz.tech.pojo.Performance;
import app.quiz.tech.pojo.StudentScore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sambhavj
 */
public class PerformanceDAO {
    private static Connection conn;
    private static PreparedStatement ps;
    public static void addPerformance(Performance per) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("insert into performance values(?,?,?,?,?,?,?)");
        ps.setString(1, per.getUserId());
        ps.setString(2, per.getExamId());
        ps.setInt(3, per.getRight());
        ps.setInt(4, per.getWrong());
        ps.setInt(5, per.getUnattempted());
        ps.setDouble(6, per.getPer());
        ps.setString(7, per.getLanguage());
        ps.executeUpdate();
    }
    
    public static List<String> getPerformanceExamIdByUserId(String userId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("select examid from performance where userid=?");
        ps.setString(1, userId);
        List<String> examList = new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            examList.add(rs.getString(1));
        }
        return examList;
    }
 
    public static ArrayList<String> getAllStudentId() throws SQLException
    {
        conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select distinct(userid) from performance");
        ArrayList<String> studentIdList = new ArrayList<>();
        while(rs.next())
        {
            studentIdList.add(rs.getString(1));
        }
        return studentIdList;
    }
    
    public static ArrayList<String> getAllExamId(String studentId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("select examid from performance where userid=?");
        ps.setString(1, studentId);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> examIdList=new ArrayList<>();
        while(rs.next())
        {
            examIdList.add(rs.getString(1));
        }
        return examIdList;
    }
    
    public static StudentScore getScore(String studentId, String examId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("select language,per from performance where userid=? and examid=?");
        ps.setString(1, studentId);
        ps.setString(2, examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        StudentScore ss=new StudentScore(rs.getString(1),rs.getDouble(2));
        return ss;
    }
    
    public static ArrayList<Performance> getAllData() throws SQLException
    {
        conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from performance");
        ArrayList<Performance> performanceList=new ArrayList<>();
        Performance p;
        while(rs.next())
        {
            String userId=rs.getString(1);
            String examId=rs.getString(2);
            int right=rs.getInt(3);
            int wrong=rs.getInt(4);
            int unattempted=rs.getInt(5);
            double per=rs.getDouble(6);
            String language=rs.getString(7);
            p=new Performance(examId, language, userId, right, wrong, unattempted, per);
            performanceList.add(p);
        }
        return performanceList;
    }
}
