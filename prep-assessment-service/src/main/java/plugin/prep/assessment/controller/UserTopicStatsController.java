package plugin.prep.assessment.controller;

import java.util.*;

import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class UserTopicStatsController implements UserTopicStatsApi {

    private final UserTopicStatsMapper userTopicStatsMapper;

    private final UserTopicStatsService userTopicStatsService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<UserTopicStatsGetResponse> getUserTopicStats(UserTopicStatsGetRequest request) {
        var requestModel = userTopicStatsMapper.toModel(request);

        var userTopicStats = userTopicStatsService.getUserTopic(requestModel);

        var response = userTopicStats.stream()
            .map(userTopicStatsMapper::toGetDto)
            .toList();
        return response;
    }

}
