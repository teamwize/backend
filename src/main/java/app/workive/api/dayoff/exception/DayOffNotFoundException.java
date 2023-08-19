package app.workive.api.dayoff.exception;

import app.workive.api.base.exception.BaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DayOffNotFoundException extends BaseException {
    private final long dayOffId;
}
