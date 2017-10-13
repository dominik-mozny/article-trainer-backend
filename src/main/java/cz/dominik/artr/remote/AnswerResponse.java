package cz.dominik.artr.remote;

import cz.dominik.artr.domain.QuestionToBeAnswered;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private long questionId;
    private boolean result;
    private StatisticsAnswers statisticsAnswers;
    private QuestionToBeAnswered nextQuestion;

    public AnswerResponse(long questionId, boolean result, StatisticsAnswers statisticsAnswers, QuestionToBeAnswered nextQuestion) {
        this.questionId = questionId;
        this.result = result;
        this.statisticsAnswers = statisticsAnswers;
        this.nextQuestion = nextQuestion;
    }

    public boolean isResult() {
        return result;
    }

    public long getQuestionId() {
        return questionId;
    }

    public StatisticsAnswers getStatisticsAnswers() {
        return statisticsAnswers;
    }

    public QuestionToBeAnswered getNextQuestion() {
        return nextQuestion;
    }
}
