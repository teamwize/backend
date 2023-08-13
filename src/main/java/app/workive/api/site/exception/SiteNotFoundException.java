package app.workive.api.site.exception;

import app.workive.api.base.exception.BaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SiteNotFoundException extends BaseException {

    private final long organizationId;


    private final long siteId;
}
