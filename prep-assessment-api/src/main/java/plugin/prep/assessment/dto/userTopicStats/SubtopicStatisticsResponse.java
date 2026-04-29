package plugin.prep.assessment.dto.userTopicStats;

import java.math.*;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class SubtopicStatisticsResponse {

    private String topic;

    private String subtopic;

    private Integer totalAnswered;

    private Integer correctCount;

    private Integer incorrectCount;

    private Double accuracy;

}
