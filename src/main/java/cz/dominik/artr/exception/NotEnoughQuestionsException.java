package cz.dominik.artr.exception;

/**
 * @author dominik.mozny
 */
public class NotEnoughQuestionsException extends RuntimeException {
    public NotEnoughQuestionsException(String nameOfQuestionCollection) {
        super("Not enough questions in question collection \"" + nameOfQuestionCollection + "\".");
    }
}
