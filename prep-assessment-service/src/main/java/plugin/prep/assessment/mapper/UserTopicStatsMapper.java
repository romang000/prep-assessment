package plugin.prep.assessment.mapper;

import java.math.*;

import org.mapstruct.*;

import plugin.prep.assessment.dto.userTopicStats.*;
import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.model.*;

@Mapper(componentModel = "spring")
public interface UserTopicStatsMapper {

    UserTopicStatsGetRequestModel toModel(UserTopicStatsGetRequest request);

    @Mapping(target = "accuracy", source = "accuracy", qualifiedByName = "bigDecimalToDouble")
    SubtopicStatisticsResponse toGetDto(UserTopicStatsEntity entity);

    UserTopicStatsGetAllResponse toGetAllDto(UserTopicStatsEntity entity);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal value) {
        var res = value.doubleValue() * 100;
        return res;
    }

}
