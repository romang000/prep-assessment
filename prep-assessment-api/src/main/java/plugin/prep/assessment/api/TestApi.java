package plugin.prep.assessment.api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springdoc.core.annotations.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.dto.page.*;
import plugin.prep.assessment.dto.test.*;

@Tag(name = "Test Api", description = "Тесты")
public interface TestApi {

    @PostMapping("/tests")
    @Operation(summary = "Создание теста")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Успешный ответ созданным тестом",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TestResponse.class)
                )
            }
        )
    })
    TestResponse create(@RequestBody TestCreateRequest request);

    @GetMapping("/tests")
    @Operation(summary = "Получение всех тестов с пагинацией")
    PageDto<TestResponse> getAll(@ModelAttribute @ParameterObject TestGetDto request);

}
