package app.workive.api.dayoff.service;

import app.workive.api.dayoff.domain.entity.DayOff;
import app.workive.api.dayoff.domain.request.DayOffCreateRequest;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import app.workive.api.dayoff.mapper.DayOffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DayOffService {

    private final DayOffMapper dayOffMapper;

    @Transactional
    public DayOffResponse createDayOff(Long userId, DayOffCreateRequest request) {
        var dayOff = new DayOff();
        return dayOffMapper.toDayOffResponse(dayOff);
    }
}
