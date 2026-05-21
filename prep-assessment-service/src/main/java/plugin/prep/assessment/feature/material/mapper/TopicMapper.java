package plugin.prep.assessment.feature.material.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.feature.material.dto.topic.*;
import plugin.prep.assessment.feature.material.entity.*;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicResponse toDto(TopicEntity entity);

}
