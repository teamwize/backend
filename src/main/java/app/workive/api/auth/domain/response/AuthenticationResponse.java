package app.workive.api.auth.domain.response;

import app.workive.api.user.domain.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    String accessToken;
    String refreshToken;
    UserResponse user;
}
