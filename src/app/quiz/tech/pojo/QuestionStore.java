/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.pojo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author sambhavj
 */
public class QuestionStore {
  ArrayList<Question> questionList;
  
  public QuestionStore()
  {
      questionList=new ArrayList<>();
  }
  public void addQuestion(Question q)
  {
      questionList.add(q);
  }
  public Question getQuestion(int pos)
  {
      return questionList.get(pos);
  }
  public Question getQuestionByQno(int qno)
  {
      for(Question q:questionList)
      {
          if(q.getQno()==qno)
          {
              return q;
          }
      }
      return null;
  }
  public void removeQuestion(int pos)
  {
      questionList.remove(pos);
  }
  public void setQuestionAt(int pos,Question q)
  {
      questionList.set(pos, q);
  }
  public ArrayList<Question> getAllQuestions(){
      return questionList;
  }
  public int getCount()
  {
      return questionList.size();
  }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.questionList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final QuestionStore other = (QuestionStore) obj;
        if (!Objects.equals(this.questionList, other.questionList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuestionStore{" + "questionList=" + questionList + '}';
    }
  
  
  
}
