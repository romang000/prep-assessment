package plugin.prep.assessment.dto.test;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
public class TestCreateRequest {

    private String title;

    private String description;

    @Schema(description = "TYPE_THEME, TYPE_TIME, TYPE_ONE_MISTAKE")
    private String type;

}
