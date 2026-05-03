package plugin.prep.assessment.dto.userTopicStats;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class UserTopicStatsGetAllRequest {

    private Long userId;

}
