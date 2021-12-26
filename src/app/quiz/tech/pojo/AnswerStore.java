/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.quiz.tech.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sambhavj
 */
public class AnswerStore {
    private List<Answer> answerList;

    public AnswerStore() {
        answerList=new ArrayList<>();
    }
    
    public AnswerStore(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswer(Answer ans)
    {
        answerList.add(ans);
    }
    
    public Answer getAnswer(int pos)
    {
        return answerList.get(pos);
    }
    
    public void removeAnswer(int pos)
    {
        answerList.remove(pos);
    }
    public void setAnswerAt(int pos,Answer ans)
    {
        answerList.set(pos, ans);
    }
    
    public List<Answer> getAllAnswers(){
        return answerList;
    }
    
    public int getCount()
    {
        return answerList.size();
    }
    
    public Answer getAnswerByQuestionNo(int qno)
    {
        for(Answer ans:answerList)
        {
            if(ans.getQno()==qno)
            {
                return ans;
            }
        }
        return null;
    }
    
    public int removeAnswer(Answer ans)
    {
        int pos=answerList.indexOf(ans);
        answerList.remove(pos);
        return pos;
    }
}
