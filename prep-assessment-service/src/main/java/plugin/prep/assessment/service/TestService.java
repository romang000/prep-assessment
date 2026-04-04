package plugin.prep.assessment.service;

import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import plugin.prep.assessment.entity.*;
import plugin.prep.assessment.enums.*;
import plugin.prep.assessment.repository.*;
import plugin.prep.errors.*;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public TestEntity create(String title, String description) {
        var testIsExists = testRepository.existsByTitle(title);
        if (testIsExists) {
            throw Exceptions.conflict(ErrorCode.TEST_ALREADY_EXISTS.format(title));
        }

        var test = TestEntity.builder()
            .title(title)
            .description(description)
            .build();

        var savedTest = testRepository.save(test);
        return savedTest;
    }

    public Page<TestEntity> getAll(
        int pageSize,
        int pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var tests = testRepository.findAll(pageable);
        return tests;
    }

}
