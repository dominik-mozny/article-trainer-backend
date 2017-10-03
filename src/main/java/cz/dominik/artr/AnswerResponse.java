package cz.dominik.artr;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;
    private QuestionToBeAnswered nextQuestion;

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

    public QuestionToBeAnswered getNextQuestion() {
        return nextQuestion;
    }
}
