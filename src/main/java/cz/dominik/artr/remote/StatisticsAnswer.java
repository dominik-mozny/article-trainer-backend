package cz.dominik.artr.remote;

import cz.dominik.artr.domain.PersistentStatisticsAnswer;

/**
 * @author dominik.mozny
 */
public class StatisticsAnswer {
    private String answer;
    private boolean isRightAnswer;

    public StatisticsAnswer(PersistentStatisticsAnswer persistentStatisticsAnswer) {
        this.answer = persistentStatisticsAnswer.getAnswer();
        this.isRightAnswer = persistentStatisticsAnswer.isRightAnswer();
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }
}
