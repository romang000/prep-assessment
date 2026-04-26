package plugin.prep.assessment.dto.question;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class QuestionCreateRequest {

    private Long testId;

    private String topic;

    private String subtopic;

    @Schema(description = "'EASY', 'MEDIUM', 'HARD'")
    private String difficulty;

    private String wordingQuestion;

    private Integer serialNumber;

}
