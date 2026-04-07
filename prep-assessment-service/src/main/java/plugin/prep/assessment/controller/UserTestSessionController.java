package plugin.prep.assessment.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.userTestSession.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class UserTestSessionController implements UserTestSessionApi {

    private final UserTestSessionService userTestSessionService;

    private final UserTestSessionMapper userTestSessionMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public UserTestSessionCreateResponse create(UserTestSessionCreateRequest request) {

        var userTestSession = userTestSessionService.create(
            request.getUserId(),
            request.getTestId()
        );

        var response = userTestSessionMapper.toDto(userTestSession);
        return response;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public UserTestSessionCompleteResponse setSessionComplete(UserTestSessionCompleteRequest request) {
        var userTestSessionComplete = userTestSessionService.setComplete(
            request.getId(),
            request.getIsComplete()
        );

        var response = userTestSessionMapper.toDto(userTestSessionComplete);
        return response;
    }

}
