package cz.dominik.artr.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * @author dominik.mozny
 */
public class QuestionToBeAnswered {


    @Id
    private Long id;
    private String question;
    private List<String> possibleAnswers;

    public QuestionToBeAnswered(PersistentQuestion pQuestion) {
        this.id = pQuestion.getId();
        this.question = pQuestion.getQuestion();
        this.possibleAnswers = pQuestion.getAnswers();
    }

    public QuestionToBeAnswered(long id, String question, List<String> possibleAnswers) {
        this.id = id;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }
}
