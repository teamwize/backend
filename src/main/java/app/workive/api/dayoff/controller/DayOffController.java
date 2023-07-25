package app.workive.api.dayoff.controller;

import app.workive.api.dayoff.domain.request.DayOffCreateRequest;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import app.workive.api.dayoff.service.DayOffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("days-off")
@RequiredArgsConstructor
public class DayOffController {

    private final DayOffService dayOffService;

    @PostMapping
    public DayOffResponse create(@RequestBody DayOffCreateRequest request) {
        return dayOffService.createDayOff(0L,request);
    }
}
