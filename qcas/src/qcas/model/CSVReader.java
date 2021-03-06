package qcas.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import qcas.operations.questions.Question;
import qcas.operations.questions.QuestionFIB;
import qcas.operations.questions.QuestionMultipleAnswer;
import qcas.operations.questions.QuestionMultipleChoice;
import qcas.operations.questions.QuestionTF;

/**
 * CSVReader methods to load and read a quiz in the csv format
 *
 * @author Shraddha Patel
 */
public class CSVReader {

    private File file = null;
    private ArrayList<Question> questions = new ArrayList<Question>();
    private String subjectCode;
    
    /**
     * Costructor for reading csv
     * @param file
     * @param subjectCode
     */
    public CSVReader(File file, String subjectCode) {
        this.file = file;
        this.subjectCode = subjectCode;
    }

    /**
     * Parse a csv to a object
     * @return boolean of file parsed or not
     */
    public boolean ParseCSV() {
        if (file == null) {
            return false;
        }
        Reader in;
        try {
            in = new FileReader(file);
            final CSVFormat csvFileFormat = CSVFormat.RFC4180.withIgnoreSurroundingSpaces().newFormat(',').withEscape('"');
            Iterable<CSVRecord> records = csvFileFormat.parse(in);
            for (CSVRecord record : records) {
                Question question = getQuestion(record, this.subjectCode);
                if (question != null) {
                    questions.add(question);
                }
            }
            if (questions.size() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Gets the file 
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     * gets the questions
     * @return
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    private Question getQuestion(CSVRecord record, String subjectCode) {
        Question question;
        if (record.size() <= 0) {
            return null;
        }
        String type = record.get(0);
        switch (type) {
            case "MC":
            case "MA":
                if (record.size() != 11) {
                    return null;
                }
                break;
            case "TF":
            case "FIB":
                if (record.size() != 4) {
                    return null;
                }
                break;
            default:
                return null;
        }

        String level = record.get(1);
        String description = record.get(2);

        int i = 0;
        switch (type) {
            case "MC":
                int answer = -1;
                String[] choices = new String[4];
                for (i = 0; i < 4; i++) {
                    String choice = record.get(3 + (i * 2));
                    choices[i] = choice;

                    String correct = record.get(4 + (i * 2));
                    if (correct.equals("correct")) {
                        if (answer != -1) {
                            return null;
                        } else {
                            answer = i;
                        }
                    }

                }

                if (answer != -1) {

                    question = new QuestionMultipleChoice(type, level, description, subjectCode, answer, choices);

                } else {
                    return null;
                }
                break;
            case "MA":
                int[] answerchoices = new int[4];
                choices = new String[4];
                boolean answerexist=false;
                for (i = 0; i < 4; i++) {
                    String choice = record.get(3 + (i * 2));
                    choices[i] = choice;

                    String correct = record.get(4 + (i * 2));
                    if (correct.equals("correct")) {
                        answerchoices[i]=1;
                        answerexist=true;
                    }
                }
                if (answerexist) { 
                    question = new QuestionMultipleAnswer(type, level, description, subjectCode,answerchoices, choices);
                } else {
                    return null;
                }
                break;
            case "TF":
                boolean choice;
                if (record.get(3).equals("true")) {
                    choice = true;
                } else if (record.get(3).equals("false")) {
                    choice = false;
                } else {
                    return null;
                }
                question = new QuestionTF(type, level, description, subjectCode, choice);
                break;
            case "FIB":
                if (!(record.get(3).length() > 0)) {
                    return null;
                }
                question = new QuestionFIB(type, level, description, subjectCode, record.get(3));
                break;
            default:
                return null;

        }
        return question;
    }

}
