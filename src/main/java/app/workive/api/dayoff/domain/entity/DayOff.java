package app.workive.api.dayoff.domain.entity;

import app.workive.api.base.domain.entity.BaseAuditEntity;
import app.workive.api.dayoff.domain.DayOffStatus;
import app.workive.api.dayoff.domain.DayOffType;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "days_off")
public class DayOff extends BaseAuditEntity {
    @Id
    @GeneratedValue(generator = "day_off_id_seq_generator")
    @SequenceGenerator(name = "day_off_id_seq_generator", sequenceName = "day_off_id_seq", allocationSize = 1)
    protected Long id;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private DayOffStatus status;

    @Enumerated(EnumType.STRING)
    private DayOffType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

}