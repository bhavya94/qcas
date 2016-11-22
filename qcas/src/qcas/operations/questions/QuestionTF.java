
package qcas.operations.questions;

/**
 * QuestionTF a class dealing with true or false question
 *
 * @author Shraddha Patel
 */
public class QuestionTF extends Question{
    private boolean answer;
    
    public QuestionTF(String id, String type, String level, String description, String subjectCode,boolean answer) {
        super(id, type, level, description, subjectCode,new String[0]);
        this.answer=answer;        
    }

    public QuestionTF(String type, String level, String description, String subjectCode,boolean answer) {
        super(type, level, description, subjectCode,new String[0]);
        this.answer=answer;
    }

    
    public boolean getAnswer() {
        return answer;
    } 
    
    
    @Override
    public String toString()
    {
       return super.toString()+" answer:"+ this.getAnswer();
    }
}