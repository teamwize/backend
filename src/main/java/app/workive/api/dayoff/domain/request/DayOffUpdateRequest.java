package app.workive.api.dayoff.domain.request;

import app.workive.api.dayoff.domain.DayOffStatus;

public record DayOffUpdateRequest(DayOffStatus status) {
}
