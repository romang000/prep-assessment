package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.userTestSession.*;
import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.model.*;

@Mapper(componentModel = "spring")
public interface UserTestSessionMapper {

    @Mapping(source = "test.id", target = "testId")
    UserTestSessionCreateResponseModel toModel(UserTestSessionEntity entity);

    UserTestSessionCreateResponse toDto(UserTestSessionCreateResponseModel model);

    @Mapping(source = "test.id", target = "testId")
    UserTestSessionCompleteResponse toDto(UserTestSessionEntity entity);

}
