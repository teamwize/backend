package app.workive.api.user.domain.event;

import app.workive.api.user.domain.UserRole;
import lombok.Data;

@Data
public class UserRegisteredEvent {
    private Long userId;
    private Long organizationId;
    private Long siteId;
    private UserRole role;
    private String email;
    private String password;
}
