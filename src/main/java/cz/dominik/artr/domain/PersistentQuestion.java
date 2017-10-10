package cz.dominik.artr.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import cz.dominik.artr.remote.NewQuestion;

/**
 * @author dominik.mozny
 */
public class PersistentQuestion {
    @Id
    private String id;
    private String question;
    private String rightAnswer;
    private List<String> answers;
    private List<AnswerStatistic> answerStatistics = new ArrayList<>();

    public PersistentQuestion(NewQuestion newArticleQuestion, QuestionType questionType) {
        id = UUID.randomUUID().toString();
        question = newArticleQuestion.getQuestion();
        rightAnswer = newArticleQuestion.getRightAnswer();
        answers = questionType.getArticles();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerStatistic> getAnswerStatistics() {
        return answerStatistics;
    }

    public void setAnswerStatistics(List<AnswerStatistic> answerStatistics) {
        this.answerStatistics = answerStatistics;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
