package plugin.prep.assessment.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByTestId(Long test);

}
