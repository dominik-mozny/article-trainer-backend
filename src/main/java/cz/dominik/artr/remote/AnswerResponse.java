package cz.dominik.artr.remote;

import cz.dominik.artr.domain.QuestionToBeAnswered;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;
    private QuestionToBeAnswered nextQuestion;

    public AnswerResponse(long questionId, boolean result, QuestionToBeAnswered nextQuestion) {
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

    public QuestionToBeAnswered getNextQuestion() {
        return nextQuestion;
    }
}
