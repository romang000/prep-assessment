package plugin.prep.assessment.dto.userTestSession;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class UserTestSessionCreateRequest {

    private Long userId;

    private Long testId;

}
