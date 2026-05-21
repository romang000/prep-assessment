package plugin.prep.assessment.feature.material.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.material.entity.*;

@Repository
public interface LearningTrackRepository extends JpaRepository<LearningTracksEntity, Long> {

    boolean existsByCode(String code);

}
