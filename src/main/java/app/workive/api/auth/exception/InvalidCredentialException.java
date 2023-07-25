package app.workive.api.auth.exception;

import app.workive.api.base.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor

public class InvalidCredentialException extends BaseException {


    private final String email;

}
