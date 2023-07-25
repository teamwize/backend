package app.workive.api.organization.domain.entity;

import app.workive.api.base.domain.entity.BaseAuditEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Map;


@Getter
@Setter
@Entity
@Table(name = "organizations")
@NoArgsConstructor
public class Organization extends BaseAuditEntity {
    @Id
    @GeneratedValue(generator = "organization_id_seq_generator")
    @SequenceGenerator(name = "organization_id_seq_generator", sequenceName = "organization_id_seq", allocationSize = 1)
    private Long id;
    private String name;

    private String country;

    @Type(JsonType.class)
    private Map<String,Object> metadata;

    public Organization(Long organizationId) {
        this.id = organizationId;
    }
}
