package cz.dominik.artr.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dominik.mozny
 */
public interface PersistentQuestionRepository extends MongoRepository<PersistentQuestion, Long> {
    List<PersistentQuestion> findByCollection(String collection);

    List<PersistentQuestion> findByCollectionOrderByLastTimeUsedAsc(String collection, Pageable pageable);
}
