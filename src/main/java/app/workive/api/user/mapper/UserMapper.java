package app.workive.api.user.mapper;



import app.workive.api.organization.mapper.OrganizationMapper;
import app.workive.api.user.domain.entity.User;
import app.workive.api.user.domain.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrganizationMapper.class})
public interface UserMapper {
    UserResponse toUserResponse(User entity);

    List<UserResponse> toUserResponses(List<User> entities);

}
