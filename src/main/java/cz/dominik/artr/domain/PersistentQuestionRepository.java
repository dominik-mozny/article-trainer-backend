package cz.dominik.artr.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dominik.mozny
 */
public interface PersistentQuestionRepository extends MongoRepository<PersistentQuestion, Long> {
}
