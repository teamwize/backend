package app.workive.api.dayoff.domain.request;

import app.workive.api.dayoff.domain.DayOffType;

import java.time.LocalDateTime;

public record DayOffCreateRequest(
        DayOffType type,
        LocalDateTime start,
        LocalDateTime end
) {
}
