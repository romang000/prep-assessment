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
public class UserQuestionService {

    private final UserQuestionRepository userQuestionRepository;

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final TestRepository testRepository;

    public UserQuestionEntity create(
        Long userId,
        Long questionId,
        Long answerId
    ) {
        //todo: проверка существования пользователя через др сервис

        var question = questionRepository.findById(questionId)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.QUESTION_NOT_FOUND.format(questionId)));

        var answer = answerRepository.findById(answerId)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.ANSWER_NOT_FOUND.format(answerId)));

        var userQuestion = UserQuestionEntity.builder()
            .userId(userId)
            .question(question)
            .answer(answer)
            .isCorrect(false)
            .build();

        if (answer.getIsCorrect()) {
            userQuestion.setIsCorrect(true);
        }

        var savedUserQuestion = userQuestionRepository.save(userQuestion);
        return savedUserQuestion;
    }

    public List<UserQuestionEntity> getByTestId(Long testId) {
        var testIsExists = testRepository.existsById(testId);
        if (!testIsExists) {
            throw Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format(testId));
        }

        var userQuestionsByTestId = userQuestionRepository.findByTestId(testId);
        return userQuestionsByTestId;
    }

}
