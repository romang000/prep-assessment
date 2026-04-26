package plugin.prep.assessment.repository;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;

@Repository
public interface UserTopicStatsRepository extends JpaRepository<UserTopicStatsEntity, Long> {

    List<UserTopicStatsEntity> findByUserId(Long userId, Limit limit);

    UserTopicStatsEntity findByUserIdAndTopicAndSubtopic(Long userId, String topic, String subtopic);

}
