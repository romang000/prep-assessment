package plugin.prep.assessment.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import plugin.prep.assessment.entity.*;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findByQuestionId(Long id);

    boolean existsByQuestionIdAndText(Long questionId, String text);

}
