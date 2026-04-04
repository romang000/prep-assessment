package plugin.prep.assessment.mapper;

import org.mapstruct.*;

import plugin.prep.assessment.dto.test.*;
import plugin.prep.assessment.entity.*;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestResponse toDto(TestEntity entity);

}
