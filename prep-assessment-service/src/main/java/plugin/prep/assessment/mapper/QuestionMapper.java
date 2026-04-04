package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.question.*;
import plugin.prep.assessment.entity.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "test.id", target = "testId")
    QuestionResponse toDto(QuestionEntity entity);

}
