package cz.dominik.artr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dominik.mozny
 */
public class QuestionsGeneratorFr {
    private QuestionsGeneratorFr() {
    }

    private static int newId = 1;
    private static final List<String> questionTexts = Arrays.asList(
            "voiture", "table", "femme", "peur", "histoire", "porte", "année", "tête", "affaire", "place");

    private static QuestionToBeAnswered createQuestion(String question) {
        return new QuestionToBeAnswered(newId++, question, Arrays.asList("le", "la"));
    }

    public static List<QuestionToBeAnswered> generateQuestions() {
        return questionTexts.stream().map(QuestionsGeneratorFr::createQuestion).collect(Collectors.toList());
    }
}
