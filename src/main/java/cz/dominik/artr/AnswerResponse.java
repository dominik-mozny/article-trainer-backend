package cz.dominik.artr;

/**
 * @author dominik.mozny
 */
public class AnswerResponse {
    private boolean result;

    public AnswerResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }
}
