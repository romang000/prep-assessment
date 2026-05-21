package plugin.prep.assessment.feature.tests.service;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.material.repository.*;
import plugin.prep.assessment.feature.tests.dto.test.*;
import plugin.prep.assessment.feature.tests.entity.*;
import plugin.prep.assessment.feature.tests.enums.*;
import plugin.prep.assessment.feature.tests.mapper.*;
import plugin.prep.assessment.feature.tests.repository.*;
import plugin.prep.assessment.feature.tests.specification.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    private final TopicRepository topicRepository;

    private final TestMapper testMapper;

    private final LearningTrackRepository learningTrackRepository;

    public TestResponse create(TestCreateRequest request) {
        var testIsExists = testRepository.existsByTitle(request.getTitle());
        if (testIsExists) {
            throw Exceptions.conflict(ErrorCode.TEST_ALREADY_EXISTS.format(request.getTitle()));
        }

        var typeEnum = TypeTestEnum.valueOf(request.getType());
        //todo: очевидно елси будет null то 500
        var gradeEnum = TestGradeEnum.valueOf(request.getGrade());

        var topic = topicRepository.findById(request.getTopicId())
            .orElseThrow(() -> Exceptions.badRequest(
                "Топик не найден: %s".formatted(request.getTopicId())
            ));

        var test = TestEntity.builder()
            .title(request.getTitle())
            .description(request.getDescription())
            .type(typeEnum)
            .grade(gradeEnum)
            .topic(topic)
            .build();

        if (typeEnum.equals(TypeTestEnum.DIAGNOSTIC)) {
            if (request.getLearningTrackId() == null) {
                throw Exceptions.badRequest("Для теста типа: DIAGNOSTIC обязательно указывать направление подготовки");
            }

            var learningTrack = learningTrackRepository
                .findById(request.getLearningTrackId())
                .orElseThrow(() -> Exceptions.badRequest(
                    "Направление подготовки не найдено: %s".formatted(request.getLearningTrackId())
                ));

            var testByLearningTrack = testRepository.findByLearningTrackId(learningTrack.getId());
            if (testByLearningTrack != null) {
                throw Exceptions.conflict(
                    "Тест для этого направления уже создан: learningTrackId=%s"
                        .formatted(learningTrack.getId()));
            }
            test.setLearningTrack(learningTrack);
        }

        var savedTest = testRepository.save(test);
        return testMapper.toDto(savedTest);
    }

    public Page<TestEntity> getAll(TestGetDto request) {
        Pageable pageable = PageRequest.of(
            request.getPageNumber(),
            request.getPageSize()
        );

        TypeTestEnum type = request.getType() == null || request.getType().isBlank()
            ? TypeTestEnum.REGULAR
            : TypeTestEnum.valueOf(request.getType());

        Specification<TestEntity> specification = Specification
            .where(TestSpecification.hasType(type));

        if (request.getGrade() != null && !request.getGrade().isBlank()) {
            TestGradeEnum grade = TestGradeEnum.valueOf(request.getGrade());

            specification = specification.and(
                TestSpecification.hasGrade(grade)
            );
        }

        if (request.getTopicId() != null) {
            specification = specification.and(
                TestSpecification.hasTopicId(request.getTopicId())
            );
        }

        return testRepository.findAll(specification, pageable);
    }

    public TestResponse getByTrack(Long trackId) {

        var learningTrackIsExists = learningTrackRepository.existsById(trackId);
        if (!learningTrackIsExists) {
            throw Exceptions.notFound("Направление подготовки не найдено: trackId=%s".formatted(trackId));
        }

        var test = testRepository.findByLearningTrackId(trackId);
        if (test == null) {
            throw Exceptions.notFound("Тест для этого направления не найден: trackId=%s".formatted(trackId));
        }
        return testMapper.toDto(test);
    }

}
