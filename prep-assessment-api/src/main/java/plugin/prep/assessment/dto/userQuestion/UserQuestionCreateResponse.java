package plugin.prep.assessment.dto.userQuestion;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class UserQuestionCreateResponse {

    private Long id;

    private Long userId;

    private Long questionId;

    private Long answerId;

    private Boolean isCorrect;

}
