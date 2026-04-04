package plugin.prep.assessment.dto.question;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class QuestionCreateResponse {

    private Long id;

    private Long testId;

    private String topic;

    private String wordingQuestion;

    private Integer serialNumber;

}
