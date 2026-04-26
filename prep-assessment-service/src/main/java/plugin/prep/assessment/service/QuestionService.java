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

    //todo: не параметры а модель
    public QuestionEntity create(
        Long testId,
        String topic,
        String subtopic,
        String difficulty,
        String wordingQuestion,
        Integer serialNumber
    ) {
        var test = testRepository
            .findById(testId)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format()));

        var difficultyEnum = QuestionDifficultyEnum.fromValue(difficulty);

        var question = QuestionEntity.builder()
            .topic(topic)
            .subtopic(subtopic)
            .test(test)
            .difficulty(difficultyEnum)
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
