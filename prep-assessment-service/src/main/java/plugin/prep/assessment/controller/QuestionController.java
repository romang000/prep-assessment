package plugin.prep.assessment.controller;

import java.util.*;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.question.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class QuestionController implements QuestionApi {

    private final QuestionService questionService;

    private final QuestionMapper questionMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse create(QuestionCreateRequest request) {
        var question = questionService.create(
            request.getTestId(),
            request.getTopic(),
            request.getWordingQuestion(),
            request.getSerialNumber()
        );

        var response = questionMapper.toDto(question);
        return response;
    }

    @Override
    public List<QuestionResponse> getByTestId(Long testId) {
        var questions = questionService.getByTestId(testId);
        var response = questions.stream()
            .map(questionMapper::toDto)
            .toList();
        return response;
    }

}
