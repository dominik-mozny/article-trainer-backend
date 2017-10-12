package cz.dominik.artr.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    @Autowired
    private IdGenerator idGenerator;

    private static Random RANDOM_GENERATOR = new Random();


    public AddQuestionsAnswer addAllArticleQuestions(NewQuestions questions, QuestionType questionType) {
        Optional<String> invalidQuestionReason = invalidQuestionReason(questions.getQuestions(), questionType);
        if (invalidQuestionReason.isPresent()) {
            LOGGER.warn("Problem occurred while saving questions.");
            return AddQuestionsAnswer.failure(invalidQuestionReason.get());
        }
        else {
            List<PersistentQuestion> questionsToPersist = questions.getQuestions().stream()
                    .map(q -> new PersistentQuestion(idGenerator.getAndIncrementNextId(), q, questionType))
                    .collect(Collectors.toList());
            persistentQuestionRepository.save(questionsToPersist);
            LOGGER.info("Questions saved successfully.");
            return AddQuestionsAnswer.success();
        }
    }

    private Optional<String> invalidQuestionReason(List<NewQuestion> questions, QuestionType questionType) {

        List<NewQuestion> invalidQuestions = questions.stream()
                .filter(q -> !questionType.getArticles().contains(q.getRightAnswer())).collect(Collectors.toList());
        if (invalidQuestions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(invalidQuestions.stream()
                .map(i -> "q: " + i.getQuestion() + "a: " + i.getRightAnswer())
                .collect(Collectors.joining(", ", "Invalid questions: ", ".")));
    }

    public List<PersistentQuestion> getNextQuestionsToAnswer(String nameOfQuestionCollection, int noOfQuestions) {
        List<PersistentQuestion> first100ByCollection = persistentQuestionRepository.findFirst100ByCollection(nameOfQuestionCollection);
        List<PersistentQuestion> selectedQuestions = new ArrayList<>();
        List<Integer> usedIndexes = new ArrayList<>();
        while (usedIndexes.size() < noOfQuestions && usedIndexes.size() < first100ByCollection.size()) {
            int questionIndex = RANDOM_GENERATOR.nextInt(first100ByCollection.size());
            if (usedIndexes.contains(questionIndex)) {
                continue;
            }
            selectedQuestions.add(first100ByCollection.get(questionIndex));
            usedIndexes.add(questionIndex);
        }
        return selectedQuestions;
    }

    public PersistentQuestion getNextQuestionToAnswer(String nameOfQuestionCollection) {
        System.out.println(nameOfQuestionCollection);
        List<PersistentQuestion> first100ByCollection = persistentQuestionRepository.findFirst100ByCollection(nameOfQuestionCollection);
        int questionIndex = RANDOM_GENERATOR.nextInt(first100ByCollection.size() - 1);
        return first100ByCollection.get(questionIndex);
    }
}
