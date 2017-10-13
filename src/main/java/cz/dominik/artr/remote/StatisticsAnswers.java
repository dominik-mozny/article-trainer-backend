package cz.dominik.artr.remote;

import java.util.List;
import java.util.stream.Collectors;

import cz.dominik.artr.domain.PersistentStatisticsAnswer;

/**
 * @author dominik.mozny
 */
public class StatisticsAnswers {
    private List<StatisticsAnswer> statisticsAnswers;

    public StatisticsAnswers(List<PersistentStatisticsAnswer> persistentStatisticsAnswers) {
        statisticsAnswers = persistentStatisticsAnswers.stream()
                .map(StatisticsAnswer::new).collect(Collectors.toList());
    }

    public List<StatisticsAnswer> getStatisticsAnswers() {
        return statisticsAnswers;
    }
}
