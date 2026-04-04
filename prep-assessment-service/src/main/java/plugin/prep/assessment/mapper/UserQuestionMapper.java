package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.userQuestion.*;
import plugin.prep.assessment.entity.*;

@Mapper(componentModel = "spring")
public interface UserQuestionMapper {

    @Mapping(source = "answer.id", target = "answerId")
    @Mapping(source = "question.id", target = "questionId")
    UserQuestionCreateResponse toDto(UserQuestionEntity entity);

    @Mapping(source = "answer.id", target = "answerId")
    @Mapping(source = "question.id", target = "questionId")
    UserQuestionGetResponse toGetDto(UserQuestionEntity entity);

}
