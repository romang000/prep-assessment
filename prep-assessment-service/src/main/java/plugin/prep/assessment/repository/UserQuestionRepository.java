package plugin.prep.assessment.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;

@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestionEntity, Long> {

    @Query(value = """
            select uq.* from user_questions uq
            join questions q on q.id = uq.question_id
            where q.test_id = :testId
        """, nativeQuery = true)
    List<UserQuestionEntity> findByTestId(@Param("testId") Long testId);

}
