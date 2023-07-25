package app.workive.api.user.domain.request;

import app.workive.api.user.domain.UserStatus;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class UserChangeStatusRequest {
    @NotNull
    private UserStatus status;
}
