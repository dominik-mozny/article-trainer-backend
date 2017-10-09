package cz.dominik.artr.question;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dominik.mozny
 */
public interface QuestionRepository extends MongoRepository<Question, Long> {
}
