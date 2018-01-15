package cz.dominik.artr.remote;

import java.util.List;

import cz.dominik.artr.domain.QuestionToBeAnswered;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean userAnswerResult;
    private List<StatisticsAnswer> statisticsAnswers;
    private QuestionToBeAnswered nextQuestion;

    public AnswerResponse(long questionId, boolean userAnswerResult, List<StatisticsAnswer> statisticsAnswers, QuestionToBeAnswered nextQuestion) {
        this.questionId = questionId;
        this.userAnswerResult = userAnswerResult;
        this.statisticsAnswers = statisticsAnswers;
        this.nextQuestion = nextQuestion;
    }

    public boolean isUserAnswerResult() {
        return userAnswerResult;
    }

    public long getQuestionId() {
        return questionId;
    }

    public List<StatisticsAnswer> getStatisticsAnswers() {
        return statisticsAnswers;
    }

    public QuestionToBeAnswered getNextQuestion() {
        return nextQuestion;
    }
}
