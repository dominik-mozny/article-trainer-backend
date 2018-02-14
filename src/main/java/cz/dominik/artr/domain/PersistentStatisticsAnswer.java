package cz.dominik.artr.domain;

import java.time.LocalDateTime;

/**
 * @author dominik.mozny
 */
public class PersistentStatisticsAnswer {
    private boolean correct;
    private String userAnswer;
    private LocalDateTime answerDateTime;

    public PersistentStatisticsAnswer(boolean correct, String userAnswer) {
        this.userAnswer = userAnswer;
        this.correct = correct;
        this.answerDateTime = LocalDateTime.now();
    }

    public boolean isCorrect() {
        return correct;
    }

    public LocalDateTime getAnswerDateTime() {
        return answerDateTime;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}
