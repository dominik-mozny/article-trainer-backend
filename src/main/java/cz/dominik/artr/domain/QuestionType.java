package cz.dominik.artr.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author dominik.mozny
 */
public enum QuestionType {
    DE(Arrays.asList("der", "die", "das")),
    FR(Arrays.asList("le", "la"));

    private List<String> articles;

    public List<String> getArticles() {
        return articles;
    }

    QuestionType(List<String> articles) {
        this.articles = articles;

    }
}
