package cz.dominik.artr.domain;

import java.time.LocalDateTime;

/**
 * @author dominik.mozny
 */
public class AnswerStatistic {
    private boolean rightAnswer;
    private LocalDateTime answerDateTime;

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public LocalDateTime getAnswerDateTime() {
        return answerDateTime;
    }

    public void setAnswerDateTime(LocalDateTime answerDateTime) {
        this.answerDateTime = answerDateTime;
    }
}
