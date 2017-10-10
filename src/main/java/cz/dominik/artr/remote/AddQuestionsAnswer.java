package cz.dominik.artr.remote;

/**
 * @author dominik.mozny
 */
public class AddQuestionsAnswer {
    private final boolean isSuccess;
    private final String failureReason;

    private AddQuestionsAnswer(boolean isSuccess, String failureReason) {
        this.isSuccess = isSuccess;
        this.failureReason = failureReason;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public static AddQuestionsAnswer success() {
        return new AddQuestionsAnswer(true, null);
    }

    public static AddQuestionsAnswer failure(String failureReason) {
        return new AddQuestionsAnswer(false, failureReason);
    }
}
