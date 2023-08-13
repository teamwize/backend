package app.workive.api.user.domain.entity;


import app.workive.api.base.domain.entity.BaseAuditEntity;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.site.domain.entity.Site;
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
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String email;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
