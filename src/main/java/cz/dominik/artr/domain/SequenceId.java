package cz.dominik.artr.domain;

import org.springframework.data.annotation.Id;

/**
 * Long ID generator for all collections
 *
 * @author dominik.mozny
 */
public class SequenceId {
    public static final String SEQUENCE_ID = "sequenceId";
    @Id
    private String sequenceId;

    public SequenceId() {
        this.sequenceId = SEQUENCE_ID;
        nextId = 0;
    }

    private long nextId;

    public long getNextId() {
        return nextId;
    }

    public void incrementNextId() {
        nextId++;
    }
}
