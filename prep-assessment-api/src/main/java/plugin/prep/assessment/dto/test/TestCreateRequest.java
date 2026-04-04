package plugin.prep.assessment.dto.test;

import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class TestCreateRequest {

    private String title;

    private String description;

}
