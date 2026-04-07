package plugin.prep.assessment.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;

@Repository
public interface UserTestSessionRepository extends JpaRepository<UserTestSessionEntity, Long> {

    List<UserTestSessionEntity> findByUserIdAndTestIdAndIsActiveIsTrue(Long userId, Long testId);

}
