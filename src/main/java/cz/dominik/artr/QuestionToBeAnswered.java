package cz.dominik.artr;

import java.util.List;

/**
 * @author dominik.mozny
 */
public class QuestionToBeAnswered {
    private long id;
    private String question;
    private List<String> answers;

    public QuestionToBeAnswered(long id, String question, List<String> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
