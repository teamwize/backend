package app.workive.api.organization.domain.event;

import app.workive.api.organization.domain.response.OrganizationResponse;
import app.workive.api.user.domain.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganizationCreatedEvent {
    private OrganizationResponse organization;
    private UserResponse user;
}
