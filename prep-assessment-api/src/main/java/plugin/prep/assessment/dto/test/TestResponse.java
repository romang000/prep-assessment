package plugin.prep.assessment.dto.test;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class TestResponse {

    private Long id;

    private String title;

    private String description;

    private String type;

}
