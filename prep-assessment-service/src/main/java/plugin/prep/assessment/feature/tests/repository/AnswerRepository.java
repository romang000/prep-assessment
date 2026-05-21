package plugin.prep.assessment.feature.tests.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import plugin.prep.assessment.feature.tests.entity.*;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findByQuestionId(Long id);

    boolean existsByQuestionIdAndText(Long questionId, String text);

}
