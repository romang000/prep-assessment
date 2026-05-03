package plugin.prep.assessment.dto.userTopicStats;

import java.math.*;
import java.time.*;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class UserTopicStatsGetAllResponse {

    private Long userId;

    private String topic;

    private String subtopic;

    private Integer totalAnswered;

    private Integer correctCount;

    private Integer incorrectCount;

    private BigDecimal accuracy;

    private OffsetDateTime lastAnsweredAt;

}
