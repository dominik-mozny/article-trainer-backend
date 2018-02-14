package cz.dominik.artr.remote;

import cz.dominik.artr.domain.PersistentStatisticsAnswer;

/**
 * @author dominik.mozny
 */
public class StatisticsAnswer {
    private String userAnswer;
    private boolean correct;

    public StatisticsAnswer(PersistentStatisticsAnswer persistentStatisticsAnswer) {
        this.userAnswer = persistentStatisticsAnswer.getUserAnswer();
        this.correct = persistentStatisticsAnswer.isCorrect();
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }
}
