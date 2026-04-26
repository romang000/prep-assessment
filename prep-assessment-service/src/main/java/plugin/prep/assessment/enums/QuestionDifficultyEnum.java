package plugin.prep.assessment.enums;

import plugin.prep.errors.*;

public enum QuestionDifficultyEnum {
    EASY,
    MEDIUM,
    HARD;

    public static QuestionDifficultyEnum fromValue(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return QuestionDifficultyEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {

            //todo: refactor message and http code
            throw Exceptions.badRequest("несуществующая сложность");
        }
    }
}
