package plugin.prep.assessment.dto.userTopicStats;

import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class UserTopicStatsGetResponse {

    private Long id;

    private Long userId;

    private String topic;

    private String subtopic;

    private Integer totalAnswered;

    private Integer correctCount;

    private Integer incorrectCount;

    private Double accuracy;

    private OffsetDateTime lastAnsweredAt;

}
