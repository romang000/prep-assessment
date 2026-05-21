package plugin.prep.assessment.feature.material.service;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.material.dto.topic.*;
import plugin.prep.assessment.feature.material.entity.*;
import plugin.prep.assessment.feature.material.repository.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicEntity create(TopicCreateRequest request) {
        if (topicRepository.existsByTitle(request.getTitle())) {
            throw Exceptions.conflict("Тема уже существует: title=%s".formatted(request.getTitle()));
        }

        var topic = TopicEntity.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .build();

        return topicRepository.save(topic);
    }

    public Page<TopicEntity> getAll(TopicGetRequest request) {
        Pageable pageable = PageRequest.of(
            request.getPageNumber(),
            request.getPageSize()
        );

        return topicRepository.findAll(pageable);
    }

    public TopicEntity getById(Long id) {
        return topicRepository.findById(id)
            .orElseThrow(() -> Exceptions.notFound("Тема не найдена: id=%s".formatted(id)));
    }

    public TopicEntity update(Long id, TopicUpdateRequest request) {
        var topic = getById(id);

        if (!topic.getTitle().equals(request.getTitle()) && topicRepository.existsByTitle(request.getTitle())) {
            throw Exceptions.conflict("Тема уже существует: title=%s".formatted(request.getTitle()));
        }

        topic.setTitle(request.getTitle());
        topic.setDescription(request.getDescription());

        return topicRepository.save(topic);
    }

    public void delete(Long id) {
        var topic = getById(id);
        topicRepository.delete(topic);
    }

}
