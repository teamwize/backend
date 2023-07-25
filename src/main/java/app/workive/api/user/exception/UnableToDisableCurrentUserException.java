package app.workive.api.user.exception;

import app.workive.api.base.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class UnableToDisableCurrentUserException extends BaseException {
    private final long targetUserId;
}
