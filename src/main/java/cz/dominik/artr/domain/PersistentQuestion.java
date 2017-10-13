package cz.dominik.artr.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import cz.dominik.artr.remote.NewQuestion;

/**
 * @author dominik.mozny
 */
public class PersistentQuestion {
    @Id
    private long id;
    private String question;
    private String rightAnswer;
    private List<String> answers;
    private List<PersistentStatisticsAnswer> persistentStatisticsAnswers = new ArrayList<>();
    private String collection;
    private int noOfAnswers;
    @Indexed
    private LocalDateTime lastTimeUsed;

    public PersistentQuestion() {
    }

    public PersistentQuestion(long id, NewQuestion newArticleQuestion, QuestionType questionType) {
        this.id = id;
        question = newArticleQuestion.getQuestion();
        rightAnswer = newArticleQuestion.getRightAnswer();
        answers = questionType.getArticles();
        this.collection = questionType.toString();
        noOfAnswers = 0;
        lastTimeUsed = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<PersistentStatisticsAnswer> getPersistentStatisticsAnswers() {
        return persistentStatisticsAnswers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCollection() {
        return collection;
    }

    public int getNoOfAnswers() {
        return noOfAnswers;
    }

    public LocalDateTime getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsedNow() {
        lastTimeUsed = LocalDateTime.now();
    }

    public void incrementNumberOfAnswers() {
        noOfAnswers++;
    }

    public void addAnswerStatistics(boolean rightAnswer, String answer) {
        persistentStatisticsAnswers.add(new PersistentStatisticsAnswer(rightAnswer, answer));
    }
}
