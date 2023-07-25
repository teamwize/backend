package app.workive.api.dayoff.domain.response;

import app.workive.api.dayoff.domain.DayOffStatus;
import app.workive.api.dayoff.domain.DayOffType;

import java.time.LocalDateTime;

public record DayOffResponse(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        DayOffStatus status,
        DayOffType type) {
}
