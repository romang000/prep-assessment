package plugin.prep.assessment.controller;

import java.util.*;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.answers.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class AnswerController implements AnswerApi {

    private final AnswerService answerService;

    private final AnswerMapper answerMapper;

    @Override
    public AnswerCreateResponse create(AnswerCreateRequest request) {
        var savedAnswer = answerService.create(
            request.getQuestionId(),
            request.getText(),
            request.getIsCorrect(),
            request.getExplanation()
        );

        var response = answerMapper.toDto(savedAnswer);
        return response;
    }

    @Override
    public List<AnswerGetResponse> getByQuestionId(Long questionId) {
        var answers = answerService.getByQuestionId(questionId);

        var response = answers.stream()
            .map(answerMapper::toGetDto)
            .toList();
        return response;
    }

}
