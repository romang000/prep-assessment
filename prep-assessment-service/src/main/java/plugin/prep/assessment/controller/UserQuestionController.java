package plugin.prep.assessment.controller;

import java.util.*;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.userQuestion.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class UserQuestionController implements UserQuestionsApi {

    private final UserQuestionService userQuestionService;

    private final UserQuestionMapper userQuestionMapper;

    @Override
    public UserQuestionCreateResponse create(UserQuestionCreateRequest request) {
        var userQuestion = userQuestionService.create(
            request.getUserId(),
            request.getQuestionId(),
            request.getAnswerId()
        );

        var response = userQuestionMapper.toDto(userQuestion);
        return response;
    }

    @Override
    public List<UserQuestionGetResponse> getByTest(Long testId) {
        var userQuestions = userQuestionService.getByTestId(testId);

        var response = userQuestions.stream()
            .map(userQuestionMapper::toGetDto)
            .toList();
        return response;
    }

}
