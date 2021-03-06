package cz.dominik.artr;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.dominik.artr.domain.PersistentQuestion;
import cz.dominik.artr.domain.PersistentQuestionRepository;
import cz.dominik.artr.domain.PersistentQuestionService;
import cz.dominik.artr.domain.QuestionToBeAnswered;
import cz.dominik.artr.domain.QuestionType;
import cz.dominik.artr.remote.AddQuestionsAnswer;
import cz.dominik.artr.remote.AnswerRequest;
import cz.dominik.artr.remote.AnswerResponse;
import cz.dominik.artr.remote.NewQuestions;
import cz.dominik.artr.remote.StatisticsAnswer;

/**
 * @author dominik.mozny
 */
@RestController
@ComponentScan
@EnableAutoConfiguration
public class Controller {
    @Autowired
    private PersistentQuestionRepository persistentQuestionRepository;

    @Autowired
    private PersistentQuestionService persistentQuestionService;

    @RequestMapping(value = "/questionForms", method = RequestMethod.GET, produces = "application/json")
    public QuestionsResponse getQuestions() {
        List<PersistentQuestion> questions = persistentQuestionService.getInitialQuestions(QuestionType.FR.toString());
        return new QuestionsResponse(questions.stream().map(QuestionToBeAnswered::new).collect(Collectors.toList()));
    }


    @RequestMapping(value = "/userAnswer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AnswerResponse> sendAnswer(@RequestBody AnswerRequest answerRequest) {
        PersistentQuestion question = persistentQuestionRepository.findOne(answerRequest.getQuestionId());
        if (question == null) {
            return ResponseEntity.noContent().build();
        }
        question.incrementNumberOfAnswers();
        boolean isUserAnswerRight = question.getRightAnswer().equals(answerRequest.getAnswer());
        question.addAnswerStatistics(isUserAnswerRight, answerRequest.getAnswer());
        persistentQuestionRepository.save(question);

        List<StatisticsAnswer> statisticsAnswers = question.getPersistentStatisticsAnswers().stream()
                .map(StatisticsAnswer::new).collect(Collectors.toList());

        return ResponseEntity.ok(new AnswerResponse(
                answerRequest.getQuestionId(),
                isUserAnswerRight,
                statisticsAnswers,
                new QuestionToBeAnswered(persistentQuestionService.getNextQuestionToAnswer(QuestionType.FR.toString()))));
    }

    @RequestMapping(value = "/initFrQuestions", method = RequestMethod.POST, produces = "application/json")
    public AddQuestionsAnswer addAllFrQuestions(@RequestBody NewQuestions questions) {
        return persistentQuestionService.initArticleQuestions(questions, QuestionType.FR);
    }

    @RequestMapping(value = "/addAllDeQuestions", method = RequestMethod.POST, produces = "application/json")
    public AddQuestionsAnswer addAllDeQuestions(@RequestBody NewQuestions questions) {
        return persistentQuestionService.initArticleQuestions(questions, QuestionType.DE);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
