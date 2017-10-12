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
    private List<String> answers;

    public QuestionToBeAnswered(PersistentQuestion pQuestion) {
        this.id = pQuestion.getId();
        this.question = pQuestion.getQuestion();
        this.answers = pQuestion.getAnswers();
    }

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
