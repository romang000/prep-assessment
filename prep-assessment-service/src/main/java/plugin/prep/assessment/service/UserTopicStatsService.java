package plugin.prep.assessment.service;

import java.util.*;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.model.*;
import plugin.prep.assessment.repository.*;

@Service
@RequiredArgsConstructor
public class UserTopicStatsService {

    private final UserTopicStatsRepository userTopicStatsRepository;

    public List<UserTopicStatsEntity> getUserTopic(UserTopicStatsGetRequestModel requestModel) {
        var userTopicStats = userTopicStatsRepository
            .findByUserId(
                requestModel.getUserId(),
                Limit.of(requestModel.getFirstFromTop())
            );

        return userTopicStats;
    }

}
