package plugin.prep.assessment.model;

import java.time.*;

import lombok.*;

import plugin.prep.assessment.entity.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTestSessionCreateResponseModel {

    private Long id;

    private Long userId;

    private Long testId;

    private OffsetDateTime startAt;

}
