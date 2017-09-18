package cz.dominik.artr;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;

    public AnswerResponse(long questionId, boolean result) {
        this.questionId = questionId;
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public long getQuestionId() {
        return questionId;
    }
}
