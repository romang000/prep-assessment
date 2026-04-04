package plugin.prep.assessment.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.api.*;
import plugin.prep.assessment.dto.page.*;
import plugin.prep.assessment.dto.test.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.service.*;

@RestController
@RequiredArgsConstructor
public class TestController implements TestApi {

    private final TestService testService;

    private final TestMapper testMapper;

    private final PageMapper pageMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public TestResponse create(TestCreateRequest request) {
        var savedTest = testService.create(
            request.getTitle(),
            request.getDescription()
        );

        var response = testMapper.toDto(savedTest);
        return response;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PageDto<TestResponse> getAll(TestGetDto request) {
        var tests = testService.getAll(
            request.getPageSize(),
            request.getPageNumber()
        );

        var response = pageMapper.toPageDto(tests, testMapper::toDto);
        return response;
    }

}
