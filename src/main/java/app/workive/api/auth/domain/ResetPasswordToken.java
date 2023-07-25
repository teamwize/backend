package app.workive.api.auth.domain;

import lombok.Data;

@Data
public class ResetPasswordToken {
    private Long siteId;
    private Long userId;
    private String email;
    private String language;
}