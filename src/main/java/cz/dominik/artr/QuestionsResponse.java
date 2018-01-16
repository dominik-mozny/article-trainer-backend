package cz.dominik.artr;

import java.util.List;

import cz.dominik.artr.domain.QuestionToBeAnswered;

/**
 * @author dominik.mozny
 */
public class QuestionsResponse {
    private List<QuestionToBeAnswered> questionForms;

    public QuestionsResponse(List<QuestionToBeAnswered> questionForms) {
        this.questionForms = questionForms;
    }

    public List<QuestionToBeAnswered> getQuestionForms() {
        return questionForms;
    }
}
