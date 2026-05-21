package plugin.prep.assessment.feature.tests.service;

import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.feature.material.repository.*;
import plugin.prep.assessment.feature.tests.dto.question.*;
import plugin.prep.assessment.feature.tests.entity.*;
import plugin.prep.assessment.feature.tests.enums.*;
import plugin.prep.assessment.feature.tests.mapper.*;
import plugin.prep.assessment.feature.tests.repository.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final TestRepository testRepository;

    private final TopicRepository topicRepository;

    private final QuestionMapper questionMapper;

    public QuestionResponse create(QuestionCreateRequest request) {
        var test = testRepository
            .findById(request.getTestId())
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format()));

        var difficultyEnum = QuestionDifficultyEnum.fromValue(request.getGrade());
        var topic = topicRepository.findById(request.getTopicId())
            .orElseThrow(() -> Exceptions.badRequest(
                "Топик не найден id=%s".formatted(request.getTopicId()))
            );

        var question = QuestionEntity.builder()
            .topic(topic)
            .subtopic(request.getSubtopic())
            .test(test)
            .grade(difficultyEnum)
            .wordingQuestion(request.getWordingQuestion())
            .serialNumber(request.getSerialNumber())
            .build();

        var savedQuestion = questionRepository.save(question);
        return questionMapper.toDto(savedQuestion);
    }

    public List<QuestionResponse> getByTestId(Long testId) {
        var testIsExists = testRepository.existsById(testId);
        if (!testIsExists) {
            throw Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format(testId));
        }

        var questions = questionRepository.findByTestId(testId);
        return questions.stream()
            .map(questionMapper::toDto)
            .toList();
    }

}
