package app.workive.api.site.domain.entity;


import app.workive.api.base.domain.entity.BaseAuditEntity;
import app.workive.api.organization.domain.entity.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sites")
@NoArgsConstructor
public class Site extends BaseAuditEntity {
    @Id
    @GeneratedValue(generator = "site_id_seq_generator")
    @SequenceGenerator(name = "site_id_seq_generator", sequenceName = "site_id_seq", allocationSize = 1)
    private Long id;

    private boolean isDefault;
    private String name;

    private String language;
    private String timezone;

    @Embedded()
    private Location location;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;


    public Site(Long id) {
        this.id = id;
    }
}
