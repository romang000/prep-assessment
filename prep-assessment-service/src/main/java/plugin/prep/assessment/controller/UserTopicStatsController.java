package plugin.prep.assessment.controller;

import java.util.*;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class UserTopicStatsController implements UserTopicStatsApi {

    private final UserTopicStatsService userTopicStatsService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<TopicStatisticsResponse> getUserTopicStats(Long id) {
        var resp = userTopicStatsService.getAllTopic(id);
        return resp;
    }

    @Override
    public List<SubtopicStatisticsResponse> getUserTopicStatsByTopic(UserTopicStatsGetBySubtopicRequest request) {
        return userTopicStatsService.getAllByTopic(request.getUserId(), request.getTopic());
    }

}
