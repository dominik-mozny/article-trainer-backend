package cz.dominik.artr;

import java.util.List;

import cz.dominik.artr.domain.QuestionToBeAnswered;

/**
 * @author dominik.mozny
 */
public class QuestionsResponse {
    private List<QuestionToBeAnswered> questions;

    public QuestionsResponse(List<QuestionToBeAnswered> questions) {
        this.questions = questions;
    }

    public List<QuestionToBeAnswered> getQuestions() {
        return questions;
    }
}
