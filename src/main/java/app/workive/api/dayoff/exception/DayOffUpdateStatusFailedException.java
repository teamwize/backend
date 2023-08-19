package app.workive.api.dayoff.exception;

import app.workive.api.base.exception.BaseException;
import app.workive.api.dayoff.domain.DayOffStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DayOffUpdateStatusFailedException extends BaseException {
    private final long dayOffId;

    private final DayOffStatus currentStatus;
}
