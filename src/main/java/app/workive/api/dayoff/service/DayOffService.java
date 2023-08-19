package app.workive.api.dayoff.service;

import app.workive.api.base.domain.model.request.PaginationRequest;
import app.workive.api.base.domain.model.response.PagedResponse;
import app.workive.api.dayoff.domain.DayOffStatus;
import app.workive.api.dayoff.domain.entity.DayOff;
import app.workive.api.dayoff.domain.request.DayOffCreateRequest;
import app.workive.api.dayoff.domain.request.DayOffUpdateRequest;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import app.workive.api.dayoff.exception.DayOffNotFoundException;
import app.workive.api.dayoff.exception.DayOffUpdateStatusFailedException;
import app.workive.api.dayoff.mapper.DayOffMapper;
import app.workive.api.dayoff.repository.DayOffRepository;
import app.workive.api.organization.domain.entity.Organization;
import app.workive.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DayOffService {

    private final DayOffRepository dayOffRepository;
    private final DayOffMapper dayOffMapper;

    @Transactional
    public DayOffResponse createDayOff(Long organizationId, Long userId, DayOffCreateRequest request) {

        var dayOff = new DayOff()
                .setStartAt(request.start())
                .setEndAt(request.end())
                .setUser(new User(userId))
                .setOrganization(new Organization(organizationId))
                .setStatus(DayOffStatus.PENDING)
                .setType(request.type());

        dayOff = dayOffRepository.persist(dayOff);

        return dayOffMapper.toDayOffResponse(dayOff);
    }

    public PagedResponse<DayOffResponse> getDaysOff(Long organizationId, PaginationRequest pagination) {
        var paging = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), Sort.by("id").descending());
        var daysOff = dayOffRepository.findByOrganizationId(organizationId, paging);
        return buildPagedResponse(daysOff);
    }

    public PagedResponse<DayOffResponse> getDaysOff(Long organizationId, Long userId, PaginationRequest pagination) {
        var paging = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize(), Sort.by("id").descending());
        var daysOff = dayOffRepository.findByOrganizationIdAndUserId(organizationId, userId, paging);
        return buildPagedResponse(daysOff);
    }


    private PagedResponse<DayOffResponse> buildPagedResponse(Page<DayOff> daysOff) {
        var contents = dayOffMapper.toDayOffResponseList(daysOff.getContent());
        return new PagedResponse<DayOffResponse>()
                .setContents(contents)
                .setPageNumber(daysOff.getNumber())
                .setPageSize(daysOff.getSize())
                .setTotalPages(daysOff.getTotalPages())
                .setTotalContents(daysOff.getTotalElements());
    }

    @Transactional
    public DayOffResponse updateDayOff(Long userId, Long id, DayOffUpdateRequest request) throws DayOffNotFoundException, DayOffUpdateStatusFailedException {
        var dayOff = getById(userId, id);
        if (dayOff.getStatus() != DayOffStatus.PENDING) {
            throw new DayOffUpdateStatusFailedException(id, dayOff.getStatus());
        }
        dayOff.setStatus(request.status());
        dayOffRepository.update(dayOff);
        return dayOffMapper.toDayOffResponse(dayOff);
    }

    public DayOffResponse getDayOff(Long userId, Long id) throws DayOffNotFoundException {
        var dayOff = getById(userId, id);
        return dayOffMapper.toDayOffResponse(dayOff);
    }

    private DayOff getById(Long userId, Long id) throws DayOffNotFoundException {
        return dayOffRepository.findByUserIdAndId(userId, id).orElseThrow(() -> new DayOffNotFoundException(id));
    }


}
