/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.dao;

import app.quiz.tech.dbutil.DBConnection;
import app.quiz.tech.pojo.Exam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sambhavj
 */
public class ExamDAO {
    private static Connection conn;
    private static PreparedStatement ps;
    public static String getExamId() throws SQLException
    {
        conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select count(*) from exam");
        rs.next();
        int rows=rs.getInt(1);
        String examId="EX-"+(rows+1);
        return examId;
    }
    
    public static boolean addExam(Exam e) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("insert into exam values(?,?,?)");
        ps.setString(1, e.getExamId());
        ps.setString(2, e.getLanguage());
        ps.setInt(3, e.getTotalQuestions());
        int ans=ps.executeUpdate();
        return ans==1;
    }
    public static List<String> getAllExamNames() throws SQLException
    {
        conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select distinct(language) from exam");
        List<String> enameList=new ArrayList<>();
        while(rs.next())
        {
            enameList.add(rs.getString(1));
        }
        return enameList;
    }
    public static List<String> getExamIdBySubject(String subject) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("Select examid from exam where language=?");
        ps.setString(1, subject);
        ResultSet rs=ps.executeQuery();
        List<String> examIdList=new ArrayList<>();
        while(rs.next())
        {
            examIdList.add(rs.getString(1));
        }
        return examIdList;
    }
    public static List<String> getExamIdBySubject(String subject,String userId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("select examid from exam where language=? minus select examid from performance where userid=?");
        ps.setString(1,subject);
        ps.setString(2, userId);
        ResultSet rs=ps.executeQuery();
        List<String> examList=new ArrayList<>();
        while(rs.next())
        {
            examList.add(rs.getString(1));
        }
        return examList;
    }
    public static int getQuestionCountByExam(String examId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("Select total_question from exam where examid=?");
        ps.setString(1, examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public static boolean isPaperSet(String subject) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("Select count(*) from exam where language=?");
        ps.setString(1, subject);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
}
