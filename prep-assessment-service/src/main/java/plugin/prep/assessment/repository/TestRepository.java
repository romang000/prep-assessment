package plugin.prep.assessment.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    boolean existsByTitle(String title);

}
