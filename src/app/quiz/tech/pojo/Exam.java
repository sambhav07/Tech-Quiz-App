/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.pojo;

/**
 *
 * @author sambhavj
 */
public class Exam {
    
    private String examId;
    private String language;
    private int totalQuestions;

    public Exam() {
    }

    public Exam(String examId, String language, int totalQuestions) {
        this.examId = examId;
        this.language = language;
        this.totalQuestions = totalQuestions;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    @Override
    public String toString() {
        return "Exam{" + "examId=" + examId + ", language=" + language + ", totalQuestions=" + totalQuestions + '}';
    }
    
    
    
    
}
