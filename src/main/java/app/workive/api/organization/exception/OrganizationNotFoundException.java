package app.workive.api.organization.exception;

import app.workive.api.base.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class OrganizationNotFoundException extends BaseException {
    private final long organizationId;
}
