package plugin.prep.assessment.entity;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tests")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
