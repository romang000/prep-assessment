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
@Entity(name = "user_test_sessions")
public class UserTestSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private TestEntity test;

    @Column(nullable = false)
    private Instant startAt;

    @Column(nullable = false)
    private Instant endAt;

    @Column(nullable = false)
    private Integer totalSecond;

    @Column(nullable = false)
    private Boolean isCompleted;

    @CreationTimestamp
    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    @UpdateTimestamp
    private ZonedDateTime updatedAt;

}
