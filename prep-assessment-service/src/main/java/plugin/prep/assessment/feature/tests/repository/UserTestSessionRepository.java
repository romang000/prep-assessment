package plugin.prep.assessment.feature.tests.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.tests.entity.*;

@Repository
public interface UserTestSessionRepository extends JpaRepository<UserTestSessionEntity, Long> {

    List<UserTestSessionEntity> findByUserIdAndTestIdAndIsActiveIsTrue(Long userId, Long testId);

    @Modifying
    @Query(value = """
        delete from user_test_sessions
        where test_id = :testId
        """, nativeQuery = true)
    void deleteByTestId(@Param("testId") Long testId);

}
