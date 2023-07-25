package app.workive.api.dayoff.domain.entity;

import app.workive.api.base.domain.entity.BaseAuditEntity;
import app.workive.api.dayoff.domain.DayOffStatus;
import app.workive.api.dayoff.domain.DayOffType;
import app.workive.api.user.domain.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "days-off")
public class DayOff extends BaseAuditEntity {
    @Id
    @GeneratedValue(generator = "user_id_seq_generator")
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "user_id_seq", allocationSize = 1)
    protected Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    private DayOffStatus status;


    @Enumerated(EnumType.STRING)
    private DayOffType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;




}