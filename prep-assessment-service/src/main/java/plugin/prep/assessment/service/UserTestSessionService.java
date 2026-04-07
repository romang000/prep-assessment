package plugin.prep.assessment.service;

import java.time.*;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.enums.*;
import plugin.prep.assessment.mapper.*;
import plugin.prep.assessment.model.*;
import plugin.prep.assessment.repository.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class UserTestSessionService {

    private final UserTestSessionRepository userTestSessionRepository;

    private final TestRepository testRepository;

    private final UserTestSessionMapper userTestSessionMapper;

    private final QuestionRepository questionRepository;

    @Transactional
    public UserTestSessionCreateResponseModel create(
        Long userId,
        Long testId
    ) {
        //todo: проверка юзера
        var test = testRepository
            .findById(testId)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.TEST_NOT_FOUND.format(testId)));

        var userTestSession = userTestSessionRepository.findByUserIdAndTestIdAndIsActiveIsTrue(userId, testId);

//        if (!userTestSession.isEmpty()) {
//            //todo: что то придумать тут
//            // (на фронте нельзя начать тест если есть открытая сессия,
//            // либо предлагать пользователю завершить все предыдущие, либо завершать самим)
//            throw Exceptions.conflict(
//                ErrorCode.USER_TEST_SESSION_NOT_COMPLETE.format(userTestSession.getFirst().getId()));
//        }

        var now = OffsetDateTime.now();
        var newUserTestSession = UserTestSessionEntity.builder()
            .userId(userId)
            .test(test)
            .startAt(now)
            .isCompleted(false)
            .isActive(true)
            .build();

        var questionsByTestId = questionRepository.findByTestId(testId);

        if (questionsByTestId.isEmpty()) {
            return new UserTestSessionCreateResponseModel();
        }

        var savedTestSession = userTestSessionRepository.save(newUserTestSession);

        var userTestSessionModel = userTestSessionMapper.toModel(savedTestSession);
        return userTestSessionModel;
    }

    @Transactional
    public UserTestSessionEntity setComplete(
        Long id,
        Boolean isComplete
    ) {
        var userTestSession = userTestSessionRepository
            .findById(id)
            .orElseThrow(() -> Exceptions.notFound(ErrorCode.USER_TEST_SESSION_NOT_FOUND.format(id)));

        if (userTestSession.getIsCompleted()) {
            throw Exceptions.conflict(ErrorCode.USER_TEST_SESSION_ALREADY_COMPLETE.format(id));
        }

        userTestSession.setEndAt(OffsetDateTime.now());
        userTestSession.setIsCompleted(isComplete);
        userTestSession.setIsActive(false);

        var startAt = userTestSession.getStartAt();
        var endAt = userTestSession.getEndAt();
        var totalSecond = Duration.between(startAt, endAt).getSeconds();

        userTestSession.setTotalSecond((int) totalSecond);

        var savedTestSession = userTestSessionRepository.save(userTestSession);
        return savedTestSession;
    }

}
