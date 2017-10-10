package cz.dominik.artr;

import java.util.List;

import cz.dominik.artr.domain.Question;

/**
 * @author dominik.mozny
 */
public class QuestionsResponse {
    private List<Question> questions;

    public QuestionsResponse(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
