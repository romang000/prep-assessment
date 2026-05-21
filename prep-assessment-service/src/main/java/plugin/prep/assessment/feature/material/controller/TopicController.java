package plugin.prep.assessment.feature.material.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import plugin.prep.assessment.feature.material.api.*;
import plugin.prep.assessment.feature.material.dto.*;
import plugin.prep.assessment.feature.material.dto.topic.*;
import plugin.prep.assessment.feature.material.mapper.*;
import plugin.prep.assessment.feature.material.service.*;

@RestController
@RequiredArgsConstructor
public class TopicController implements TopicApi {

    private final TopicService topicService;

    private final TopicMapper topicMapper;

    private final PageMapper pageMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TopicResponse create(TopicCreateRequest request) {
        var topic = topicService.create(request);
        return topicMapper.toDto(topic);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public PageDto<TopicResponse> getAll(TopicGetRequest request) {
        var topics = topicService.getAll(request);
        return pageMapper.toPageDto(topics, topicMapper::toDto);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public TopicResponse getById(Long id) {
        var topic = topicService.getById(id);
        return topicMapper.toDto(topic);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public TopicResponse update(Long id, TopicUpdateRequest request) {
        var topic = topicService.update(id, request);
        return topicMapper.toDto(topic);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        topicService.delete(id);
    }

}
