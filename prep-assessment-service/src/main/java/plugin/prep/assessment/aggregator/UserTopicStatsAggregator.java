package plugin.prep.assessment.aggregator;

import java.util.*;

import org.springframework.stereotype.*;

import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.entity.*;

@Component
public class UserTopicStatsAggregator {

    public TopicStatisticsResponse buildTopicStatistics(
        String topic,
        List<UserTopicStatsEntity> entities
    ) {
        var totalAnswered = entities.stream()
            .mapToInt(UserTopicStatsEntity::getTotalAnswered)
            .sum();

        var correctCount = entities.stream()
            .mapToInt(UserTopicStatsEntity::getCorrectCount)
            .sum();

        var incorrectCount = entities.stream()
            .mapToInt(UserTopicStatsEntity::getIncorrectCount)
            .sum();

        var accuracy = calculateAccuracy(
            totalAnswered,
            correctCount
        );

        return new TopicStatisticsResponse()
            .setTopic(topic)
            .setTotalAnswered(totalAnswered)
            .setCorrectCount(correctCount)
            .setIncorrectCount(incorrectCount)
            .setAccuracy(accuracy);
    }

    private Double calculateAccuracy(
        Integer totalAnswered,
        Integer correctCount
    ) {
        if (totalAnswered == null || totalAnswered == 0) {
            return 0.0;
        }

        return Math.round(((double) correctCount / totalAnswered) * 10000) / 100.0;
    }

}
