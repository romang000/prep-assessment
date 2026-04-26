package plugin.prep.assessment.enums;

import lombok.*;

import plugin.prep.errors.*;

@Getter
@RequiredArgsConstructor
public enum TypeTestEnum {
    THEME("TYPE_THEME"),
    TIME("TYPE_TIME"),
    ONE_MISTAKE("TYPE_ONE_MISTAKE");

    private final String type;

    public static TypeTestEnum toType(String type) {
        try {
            var resp = TypeTestEnum.valueOf(type);
            return resp;
        } catch (Exception e) {
            throw Exceptions.notFound("Неизвестный тип");
        }
    }
}
