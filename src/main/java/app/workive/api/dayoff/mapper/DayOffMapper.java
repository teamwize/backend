package app.workive.api.dayoff.mapper;

import app.workive.api.base.config.DefaultMapperConfig;
import app.workive.api.dayoff.domain.entity.DayOff;
import app.workive.api.dayoff.domain.response.DayOffResponse;
import org.mapstruct.Mapper;

@Mapper(config = DefaultMapperConfig.class)
public interface DayOffMapper {

    DayOffResponse toDayOffResponse(DayOff dayoff);

}
