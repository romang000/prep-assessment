package plugin.prep.some.dto;

import io.swagger.v3.oas.annotations.media.*;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(chain = true)
@Schema(description = "Ответ с приветствием")
public class HelloDto {

    @Schema(description = "Сообщение")
    private String message;

}
