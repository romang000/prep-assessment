package plugin.prep.assessment.service;

import java.util.*;

import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.aggregator.*;
import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.model.*;
import plugin.prep.assessment.repository.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTopicStatsService {

    private final UserTopicStatsRepository userTopicStatsRepository;

    private final UserTopicStatsMapper userTopicStatsMapper;

    private final UserTopicStatsAggregator userTopicStatsAggregator;

    public List<UserTopicStatsEntity> getUserTopic(UserTopicStatsGetRequestModel requestModel) {
        var userTopicStats = userTopicStatsRepository
            .findByUserId(
                requestModel.getUserId(),
                Limit.of(requestModel.getFirstFromTop())
            );

        return userTopicStats;
    }

    public List<SubtopicStatisticsResponse> getAllByTopic(Long userId, String topic) {
        var statistics = userTopicStatsRepository.findByUserIdAndTopic(userId, topic);
        log.info("{}", statistics.stream().map(UserTopicStatsEntity::getAccuracy).toList());
        var response = statistics.stream()
            .map(userTopicStatsMapper::toGetDto)
            .toList();

        log.info("{}", response);
        return response;
    }

    public List<TopicStatisticsResponse> getAllTopic(Long userId) {
        var statistics = userTopicStatsRepository.findByUserId(userId);

        var resp = statistics.stream()
            .map(s -> userTopicStatsAggregator
                .buildTopicStatistics(
                    s.getTopic(),
                    statistics
                )
            ).toList();

        return resp;
    }

    public List<UserTopicStatsGetAllResponse> getAll(UserTopicStatsGetAllRequest request) {
        var userTopicStats = userTopicStatsRepository.findByUserId(request.getUserId());
        var response = userTopicStats.stream()
            .map(userTopicStatsMapper::toGetAllDto)
            .toList();
        return response;
    }

}
