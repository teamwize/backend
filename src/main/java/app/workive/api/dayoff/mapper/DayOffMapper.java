package app.workive.api.dayoff.mapper;

import app.workive.api.base.config.DefaultMapperConfig;
import app.workive.api.base.domain.model.response.PagedResponse;
import app.workive.api.dayoff.domain.entity.DayOff;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = DefaultMapperConfig.class)
public interface DayOffMapper {

    DayOffResponse toDayOffResponse(DayOff dayoff);

    List<DayOffResponse> toDayOffResponseList(List<DayOff> dayoff);



}
