package plugin.prep.assessment.api;

import java.util.*;

import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.dto.answers.*;

@Tag(name = "Answers Api", description = "Ответы на вопрос")
public interface AnswerApi {

    @PostMapping("/answers")
    AnswerCreateResponse create(@RequestBody AnswerCreateRequest request);

    @GetMapping("/answers/questions/{questionId}")
    List<AnswerGetResponse> getByQuestionId(@PathVariable Long questionId);

}
