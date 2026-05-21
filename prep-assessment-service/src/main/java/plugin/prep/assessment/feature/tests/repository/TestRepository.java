package plugin.prep.assessment.feature.tests.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.material.entity.*;
import plugin.prep.assessment.feature.tests.entity.*;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>, JpaSpecificationExecutor<TestEntity> {

    boolean existsByTitle(String title);

    TestEntity findByLearningTrackId(Long id);

}
