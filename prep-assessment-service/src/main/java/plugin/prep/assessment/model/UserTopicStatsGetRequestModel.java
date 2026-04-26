package plugin.prep.assessment.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTopicStatsGetRequestModel {

    private Long userId;

    private Integer firstFromTop;

}
