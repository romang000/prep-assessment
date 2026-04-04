package plugin.prep.assessment.service;

import java.util.*;

import lombok.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.enums.*;
import plugin.prep.assessment.repository.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final TestRepository testRepository;

    public QuestionEntity create(
        Long testId,
        String topic,
        String wordingQuestion,
        Integer serialNumber
    ) {
        var test = testRepository
            .findById(testId)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format()));

        var question = QuestionEntity.builder()
            .topic(topic)
            .test(test)
            .wordingQuestion(wordingQuestion)
            .serialNumber(serialNumber)
            .build();

        var savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }

    public List<QuestionEntity> getByTestId(Long testId) {
        var testIsExists = testRepository.existsById(testId);
        if (!testIsExists) {
            throw Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format(testId));
        }

        var questions = questionRepository.findByTestId(testId);
        return questions;
    }

}
