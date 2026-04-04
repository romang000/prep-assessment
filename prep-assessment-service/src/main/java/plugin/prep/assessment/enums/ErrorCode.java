package plugin.prep.assessment.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    TEST_ALREADY_EXISTS("Тест уже существует: title=%s"),
    TEST_NOT_FOUND("Тест не найден: id=%s"),
    QUESTION_NOT_FOUND("Вопрос не найден: id=%s"),
    ANSWER_ALREADY_EXISTS("Ответ уже существует: questionId=%s, text=%s"),
    ANSWER_NOT_FOUND("Ответ не найден: answerId=%s");

    private final String message;

    public String format(Object... args) {
        return message.formatted(args);
    }
}
