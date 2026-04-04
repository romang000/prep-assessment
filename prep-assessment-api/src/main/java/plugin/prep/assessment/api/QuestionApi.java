package plugin.prep.assessment.api;

import java.util.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.dto.question.*;

@Tag(name = "Question Api", description = "Вопросы для теста")
public interface QuestionApi {

    @PostMapping("/questions")
    @Operation(summary = "Создание вопроса")
    QuestionResponse create(@RequestBody QuestionCreateRequest request);

    @GetMapping("/questions/tests/{testId}")
    @Operation(summary = "Получение вопросов теста")
    List<QuestionResponse> getByTestId(@PathVariable Long testId);

}
