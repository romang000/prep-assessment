package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.model.*;

@Mapper(componentModel = "spring")
public interface UserTopicStatsMapper {

    UserTopicStatsGetRequestModel toModel(UserTopicStatsGetRequest request);

    UserTopicStatsGetResponse toGetDto(UserTopicStatsEntity entity);

}
