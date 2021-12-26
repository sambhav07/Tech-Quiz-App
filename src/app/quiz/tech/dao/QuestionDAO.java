/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.dao;

import app.quiz.tech.dbutil.DBConnection;
import app.quiz.tech.pojo.Question;
import app.quiz.tech.pojo.QuestionStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sambhavj
 */
public class QuestionDAO {
    private static Connection conn;
    private static PreparedStatement ps;
    public static void addQuestion(QuestionStore qStore) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("insert into questions values(?,?,?,?,?,?,?,?,?)");
        ArrayList<Question> questionList=qStore.getAllQuestions();
        for(Question q:questionList)
        {
            ps.setString(1, q.getExamId());
            ps.setInt(2, q.getQno());
            ps.setString(3, q.getQuestion());
            ps.setString(4, q.getAnswer1());
            ps.setString(5, q.getAnswer2());
            ps.setString(6, q.getAnswer3());
            ps.setString(7, q.getAnswer4());
            ps.setString(8, q.getCorrectAnswer());
            ps.setString(9, q.getLanguage());
            ps.executeUpdate();
        }
    }
    
    public static List<Question> getQuestionsByExamId(String examId) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("Select * from questions where examid=? order by qno");
        ps.setString(1, examId);
        ResultSet rs=ps.executeQuery();
        List<Question> quesList=new ArrayList<>();
        Question q;
        while(rs.next())
        {
            int qno=rs.getInt(2);
            String question=rs.getString(3);
            String option1=rs.getString(4);
            String option2=rs.getString(5);
            String option3=rs.getString(6);
            String option4=rs.getString(7);
            String correctAnswer=rs.getString(8);
            String language=rs.getString(9);
            q=new Question(examId,qno,question,option1,option2,option3,option4,correctAnswer,language);
            quesList.add(q);
        }
        return quesList;
    }
    
    public static void updateQuestion(QuestionStore qStore) throws SQLException
    {
        conn=DBConnection.getConnection();
        ps=conn.prepareStatement("update questions set question=?,answer1=?,answer2=?,answer3=?,answer4=?,correct_answer=?,language=? where examid=? and qno=?");
        List<Question> questionList=qStore.getAllQuestions();
        for(Question q:questionList)
        {
            ps.setString(1, q.getQuestion());
            ps.setString(2, q.getAnswer1());
            ps.setString(3, q.getAnswer2());
            ps.setString(4, q.getAnswer3());
            ps.setString(5, q.getAnswer4());
            ps.setString(6, q.getCorrectAnswer());
            ps.setString(7, q.getLanguage());
            ps.setString(8, q.getExamId());
            ps.setInt(9, q.getQno());
            ps.executeUpdate();
        }
        
    }
}
