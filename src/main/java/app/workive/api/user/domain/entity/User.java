package app.workive.api.user.domain.entity;


import app.workive.api.base.domain.entity.BaseAuditEntity;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.user.domain.UserRole;
import app.workive.api.user.domain.UserStatus;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseAuditEntity {
    @Id
    @GeneratedValue(generator = "user_id_seq_generator")
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "user_id_seq", allocationSize = 1)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected UserRole role;
    protected String email;
    protected String password;
    protected String phone;
    protected String firstName;
    protected String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    protected Organization organization;


    @Enumerated(EnumType.STRING)
    protected UserStatus status;

}
