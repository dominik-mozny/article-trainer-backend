package cz.dominik.artr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author dominik.mozny
 */
@RestController
@EnableAutoConfiguration
public class Controller {

    @RequestMapping(value = "/questions", method = RequestMethod.GET, produces = "application/json")
    public QuestionsResponse getQuestions() {
        return new QuestionsResponse(QuestionsGeneratorFr.generateQuestions());
    }


    @RequestMapping(value = "/sendAnswer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AnswerResponse> abc(@RequestBody AnswerRequest answerRequest) {
        System.out.println(answerRequest.getAnswer());
        System.out.println(answerRequest.getQuestionId());
        JsonObject json = new JsonObject();
        json.addProperty("word", "greetings from server");
        return ResponseEntity.ok(new AnswerResponse(answerRequest.getQuestionId(), "la".equals(answerRequest.getAnswer())));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
