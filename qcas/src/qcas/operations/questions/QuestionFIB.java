
package qcas.operations.questions;

/**
 * QuestionMultipleAnswer a class dealing with fill in the blanks question
 *
 * @author Shraddha Patel
 */
public class QuestionFIB extends Question{
    private String answer;
    
    public QuestionFIB(String id, String type, String level, String description, String subjectCode,String answer) {
        super(id, type, level, description, subjectCode,new String[0]);
        this.answer=answer;        
    }

    public QuestionFIB(String type, String level, String description, String subjectCode,String answer) {
        super(type, level, description, subjectCode,new String[0]);
        this.answer=answer;
    }

    
    public String getAnswer() {
        return answer;
    }  
    
    @Override
    public String toString()
    {
       return super.toString()+" answer:"+ this.getAnswer();
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
    public boolean evaluate(Question question) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
