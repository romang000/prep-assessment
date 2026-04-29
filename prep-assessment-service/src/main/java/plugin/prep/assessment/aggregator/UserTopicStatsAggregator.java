package plugin.prep.assessment.aggregator;

import java.math.*;
import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.entity.*;

@Component
public class UserTopicStatsAggregator {

    public UserTopicStatsGetResponse aggregate(
        List<UserTopicStatsEntity> statistics
    ) {
        var response = statistics.stream()
            .collect(Collectors.groupingBy(
                UserTopicStatsEntity::getTopic,
                LinkedHashMap::new,
                Collectors.toList()
            ))
            .entrySet()
            .stream()
            .map(entry -> new TopicWithSubtopicStatisticsResponse()
                .setTopic(buildTopicStatistics(
                    entry.getKey(),
                    entry.getValue()
                ))
                .setSubtopics(buildSubtopics(entry.getValue()))
            )
            .toList();

        return new UserTopicStatsGetResponse()
            .setStatistics(response);
    }

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

    private List<SubtopicStatisticsResponse> buildSubtopics(
        List<UserTopicStatsEntity> entities
    ) {
        return entities.stream()
            .map(entity -> new SubtopicStatisticsResponse()
                .setTopic(entity.getTopic())
                .setSubtopic(entity.getSubtopic())
                .setTotalAnswered(entity.getTotalAnswered())
                .setCorrectCount(entity.getCorrectCount())
                .setIncorrectCount(entity.getIncorrectCount())
                .setAccuracy(entity.getAccuracy().doubleValue())
            )
            .toList();
    }

    private Double calculateAccuracy(
        Integer totalAnswered,
        Integer correctCount
    ) {
        if (totalAnswered == null || totalAnswered == 0) {
            return 0.0;
        }

        return Math.round((
            (double) correctCount / totalAnswered) * 10000
        ) / 100.0;
    }

}
