package cz.dominik.artr;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import cz.dominik.artr.question.Question;

/**
 * @author dominik.mozny
 */
public class QuestionsGeneratorFr {
    private QuestionsGeneratorFr() {
    }

    private static long newId = 1;
    private static final List<String> questionTexts = Arrays.asList(
            "voiture", "table", "femme", "peur", "histoire", "porte", "année", "tête", "affaire", "place");

    public static Question getQuestion() {
        return createQuestion(questionTexts.get(new Random().nextInt(questionTexts.size() - 1)));
    }

    private static Question createQuestion(String question) {
        return new Question(newId++, question, Arrays.asList("le", "la"));
    }

    public static List<Question> generateQuestions() {
        return questionTexts.stream().map(QuestionsGeneratorFr::createQuestion).collect(Collectors.toList());
    }
}
