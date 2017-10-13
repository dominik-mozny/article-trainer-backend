package cz.dominik.artr.domain;

import static cz.dominik.artr.domain.StaticConfiguration.NUMBER_OF_QUESTIONS_DISPLAYED_SIMULTANEOUSLY;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cz.dominik.artr.exception.NotEnoughQuestionsException;
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


    public AddQuestionsAnswer initArticleQuestions(NewQuestions questions, QuestionType questionType) {
        int minSize = NUMBER_OF_QUESTIONS_DISPLAYED_SIMULTANEOUSLY * 4;
        if (questions.getQuestions().size() < minSize) {
            return AddQuestionsAnswer.failure("Question collection must contain at least " + minSize + " items.");
        }
        Optional<String> invalidQuestionReason = invalidQuestionReason(questions.getQuestions(), questionType);
        if (invalidQuestionReason.isPresent()) {
            LOGGER.warn("Problem occurred while saving questions.");
            return AddQuestionsAnswer.failure(invalidQuestionReason.get());
        }
        else {
            List<PersistentQuestion> questionsToPersist = questions.getQuestions().stream()
                    .map(q -> new PersistentQuestion(idGenerator.getAndIncrementNextId(), q, questionType))
                    .collect(Collectors.toList());
            List<PersistentQuestion> toBeDeleted = persistentQuestionRepository.findByCollection(questionType.toString());
            persistentQuestionRepository.delete(toBeDeleted);
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

    public List<PersistentQuestion> getInitialQuestions(String nameOfQuestionCollection) {
        //TODO potential for optimization when needed
        List<PersistentQuestion> persistentQuestions = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_QUESTIONS_DISPLAYED_SIMULTANEOUSLY; i++) {
            persistentQuestions.add(getNextQuestionToAnswer(nameOfQuestionCollection));
        }
        return persistentQuestions;
    }

    public PersistentQuestion getNextQuestionToAnswer(String nameOfQuestionCollection) {
        List<PersistentQuestion> fetchedQuestionCandidates = persistentQuestionRepository
                .findByCollectionOrderByLastTimeUsedAsc(nameOfQuestionCollection, new PageRequest(0, 2 * NUMBER_OF_QUESTIONS_DISPLAYED_SIMULTANEOUSLY));
        if (fetchedQuestionCandidates.size() != NUMBER_OF_QUESTIONS_DISPLAYED_SIMULTANEOUSLY * 2) {
            throw new NotEnoughQuestionsException(nameOfQuestionCollection);
        }
        int minNumberOfAnswers = fetchedQuestionCandidates.stream()
                .min(Comparator.comparing(PersistentQuestion::getNoOfAnswers)).get().getNoOfAnswers();
        List<PersistentQuestion> filteredQuestionCandidates = fetchedQuestionCandidates.stream()
                .filter(a -> a.getNoOfAnswers() == minNumberOfAnswers).collect(Collectors.toList());
        int selectedQuestionIndex = filteredQuestionCandidates.size() == 1 ? 0 : RANDOM_GENERATOR.nextInt(filteredQuestionCandidates.size() - 1);
        PersistentQuestion selectedQuestion = filteredQuestionCandidates.get(selectedQuestionIndex);
        selectedQuestion.setLastTimeUsedNow();
        PersistentQuestion updatedQuestion = persistentQuestionRepository.save(selectedQuestion);
        return updatedQuestion;
    }
}
