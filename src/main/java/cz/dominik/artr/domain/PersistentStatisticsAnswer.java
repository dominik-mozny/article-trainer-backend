package cz.dominik.artr.domain;

import java.time.LocalDateTime;

/**
 * @author dominik.mozny
 */
public class PersistentStatisticsAnswer {
    private boolean rightAnswer;
    private String answer;
    private LocalDateTime answerDateTime;

    public PersistentStatisticsAnswer(boolean rightAnswer, String answer) {
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.answerDateTime = LocalDateTime.now();
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public LocalDateTime getAnswerDateTime() {
        return answerDateTime;
    }

    public String getAnswer() {
        return answer;
    }
}
