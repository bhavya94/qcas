
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
    
    /**
     * Blank constructor for some operations
     */
    public QuestionFIB()
    {
       
    }

    public QuestionFIB(String type, String level, String description, String subjectCode,String answer) {
        super(type, level, description, subjectCode,new String[0]);
        this.answer=answer;
    }

    
    public String getAnswer() {
        return answer;
    }  
    
       
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
    public boolean evaluate(Question question) {
        boolean check=false;
        if(question.getId().equals(this.getId())&&(question instanceof QuestionFIB)){
            check = this.getAnswer().equalsIgnoreCase(((QuestionFIB)question).getAnswer());
        }
        return check;
    }
    
    @Override
    public Question getQuestion(String id,String questionType, String questionLevel, String questionDescription,String subjectCode,String choice1,String valid1,String choice2,String valid2,String choice3,String valid3,String choice4,String valid4) {
        Question question;
        question = new QuestionFIB(id,questionType,questionLevel, questionDescription, subjectCode,choice1);
        return question;
    }
    
    @Override
    public Question clone()
    {
        return new QuestionFIB(this.getId(),this.getType(),this.getLevel(),this.getDescription(),this.getSubjectCode(),null);
    }
    
    
}
