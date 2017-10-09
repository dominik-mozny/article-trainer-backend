package cz.dominik.artr;

import cz.dominik.artr.question.Question;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;
    private Question nextQuestion;

    public AnswerResponse(long questionId, boolean result) {
        this.questionId = questionId;
        this.result = result;
        nextQuestion = QuestionsGeneratorFr.getQuestion();
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
