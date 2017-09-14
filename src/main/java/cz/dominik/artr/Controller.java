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

    @RequestMapping(value = "/getWord", method = RequestMethod.GET, produces = "application/json")
    public String getWord() {
        JsonObject json = new JsonObject();
        json.addProperty("word", "voiture");
        JsonArray answers = new JsonArray();
        answers.add("la");
        answers.add("le");
        json.add("answers", answers);
        return json.toString();
    }

    @RequestMapping(value = "/postAnswer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> abc(@RequestBody Answer answer) {
        System.out.println(answer.getWord());
        JsonObject json = new JsonObject();
        json.addProperty("word", "greetings from server");
        return ResponseEntity.ok(json.toString());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Controller.class, args);
    }
}
