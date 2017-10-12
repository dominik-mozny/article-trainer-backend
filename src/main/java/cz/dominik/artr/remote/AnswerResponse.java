package cz.dominik.artr.remote;

import cz.dominik.artr.domain.Question;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;
    private Question nextQuestion;

    public AnswerResponse(long questionId, boolean result, Question nextQuestion) {
        this.questionId = questionId;
        this.result = result;
        this.nextQuestion = nextQuestion;
    }

    public boolean isResult() {
        return result;
    }

    public long getQuestionId() {
        return questionId;
    }

    public Question getNextQuestion() {
        return nextQuestion;
    }
}
