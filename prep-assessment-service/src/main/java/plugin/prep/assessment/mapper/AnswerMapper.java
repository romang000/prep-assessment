package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.answers.*;
import plugin.prep.assessment.entity.*;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "question.id", target = "questionId")
    AnswerCreateResponse toDto(AnswerEntity entity);

    @Mapping(source = "question.id", target = "questionId")
    AnswerGetResponse toGetDto(AnswerEntity entity);

}
