package app.workive.api.dayoff.controller;

import app.workive.api.auth.service.SecurityService;
import app.workive.api.base.domain.model.request.PaginationRequest;
import app.workive.api.base.domain.model.response.PagedResponse;
import app.workive.api.dayoff.domain.request.DayOffCreateRequest;
import app.workive.api.dayoff.domain.request.DayOffUpdateRequest;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import app.workive.api.dayoff.exception.DayOffNotFoundException;
import app.workive.api.dayoff.exception.DayOffUpdateStatusFailedException;
import app.workive.api.dayoff.service.DayOffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("days-off")
@RequiredArgsConstructor
public class DayOffController {

    private final DayOffService dayOffService;
    private final SecurityService securityService;

    @PostMapping
    public DayOffResponse create(@RequestBody DayOffCreateRequest request) {
        return dayOffService.createDayOff(securityService.getUserOrganizationId(), securityService.getUserId(), request);
    }

    @GetMapping
    public PagedResponse<DayOffResponse> getDaysOff(@ParameterObject @Valid PaginationRequest pagination) {
        return dayOffService.getDaysOff(securityService.getUserOrganizationId(), pagination);
    }

    @GetMapping("mine")
    public PagedResponse<DayOffResponse> getMineDaysOff(@ParameterObject PaginationRequest pagination) {
        return dayOffService.getDaysOff(securityService.getUserOrganizationId(), securityService.getUserId(), pagination);
    }

    @PutMapping("{id}")
    public  DayOffResponse updateDayOff(@PathVariable Long id,@RequestBody DayOffUpdateRequest request) throws DayOffNotFoundException, DayOffUpdateStatusFailedException {
        return dayOffService.updateDayOff(securityService.getUserId(),id,request);
    }

    @GetMapping("{id}")
    public DayOffResponse getDayOff(@PathVariable Long id) throws DayOffNotFoundException {
        return dayOffService.getDayOff(securityService.getUserId(),id);
    }
}
