package cz.dominik.artr.domain;

import static cz.dominik.artr.domain.SequenceId.SEQUENCE_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dominik.mozny
 */
@Service
public class IdGenerator {
    @Autowired
    private SequenceIdRepository sequenceIdRepository;

    public long getAndIncrementNextId() {
        SequenceId idGenerator = sequenceIdRepository.findOne(SEQUENCE_ID);
        if (idGenerator == null) {
            idGenerator = sequenceIdRepository.save(new SequenceId());
        }
        long nextId = idGenerator.getNextId();
        idGenerator.incrementNextId();
        sequenceIdRepository.save(idGenerator);
        return nextId;
    }

}
