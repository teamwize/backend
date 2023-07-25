package app.workive.api.user.exception;

import app.workive.api.base.exception.BaseException;
import app.workive.api.base.exception.NotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class UserNotFoundException extends BaseException {


    private long organizationId;


    private long userId;


    private String email;

    public UserNotFoundException(long organizationId, long userId) {
        this.organizationId = organizationId;
        this.userId = userId;
    }

    public UserNotFoundException(String email) {
        this.email = email;
    }
}
