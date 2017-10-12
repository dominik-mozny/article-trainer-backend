package cz.dominik.artr.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.dominik.artr.remote.AddQuestionsAnswer;
import cz.dominik.artr.remote.NewQuestion;
import cz.dominik.artr.remote.NewQuestions;

/**
 * @author dominik.mozny
 */
@Service
public class PersistentQuestionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersistentQuestionService.class);
    @Autowired
    private PersistentQuestionRepository persistentQuestionRepository;

    public AddQuestionsAnswer addAllArticleQuestions(NewQuestions questions, QuestionType questionType) {
        Optional<String> invalidQuestionReason = invalidQuestionReason(questions.getQuestions(), questionType);
        if (invalidQuestionReason.isPresent()) {
            LOGGER.warn("Problem occurred while saving questions.");
            return AddQuestionsAnswer.failure(invalidQuestionReason.get());
        }
        else {
            List<PersistentQuestion> questionsToPersist = questions.getQuestions().stream().map(q -> new PersistentQuestion(q, questionType)).collect(Collectors.toList());
            persistentQuestionRepository.save(questionsToPersist);
            LOGGER.info("Questions saved successfully.");
            return AddQuestionsAnswer.success();
        }
    }

    private Optional<String> invalidQuestionReason(List<NewQuestion> questions, QuestionType questionType) {

        List<NewQuestion> invalidQuestions = questions.stream().filter(q -> !questionType.getArticles().contains(q.getRightAnswer())).collect(Collectors.toList());
        if (invalidQuestions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(invalidQuestions.stream()
                .map(i -> "q: " + i.getQuestion() + "a: " + i.getRightAnswer())
                .collect(Collectors.joining(", ", "Invalid questions: ", ".")));
    }
}
