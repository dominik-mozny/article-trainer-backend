package cz.dominik.artr.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

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
    private List<AnswerStatistic> answerStatistics = new ArrayList<>();

    public PersistentQuestion(long id, NewQuestion newArticleQuestion, QuestionType questionType) {
        this.id = id;
        question = newArticleQuestion.getQuestion();
        rightAnswer = newArticleQuestion.getRightAnswer();
        answers = questionType.getArticles();
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<AnswerStatistic> getAnswerStatistics() {
        return answerStatistics;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
