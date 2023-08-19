package app.workive.api.user.controller;


import app.workive.api.base.exception.NotNullFieldViolationException;
import app.workive.api.auth.service.SecurityService;
import app.workive.api.user.domain.request.ChangePasswordRequest;
import app.workive.api.user.domain.request.UserChangeStatusRequest;
import app.workive.api.user.domain.request.UserUpdateRequest;
import app.workive.api.user.domain.response.UserResponse;
import app.workive.api.user.exception.IncorrectPasswordException;
import app.workive.api.user.exception.UnableToDisableCurrentUserException;
import app.workive.api.user.exception.UserAlreadyExistsException;
import app.workive.api.user.exception.UserNotFoundException;
import app.workive.api.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping("mine")
    public UserResponse getMyProfile() throws UserNotFoundException {
        return userService.getUser(securityService.getUserOrganizationId(), securityService.getUserId());
    }


    @GetMapping("{userId}")
    public UserResponse getUser(@PathVariable Long userId) throws UserNotFoundException {
        return userService.getUser(securityService.getUserOrganizationId(), userId);
    }

    @PatchMapping("{userId}")
    public UserResponse updateMyProfile(@PathVariable Long userId,@RequestBody @Valid UserUpdateRequest request)
            throws UserNotFoundException, UserAlreadyExistsException, NotNullFieldViolationException {
        var organizationId = securityService.getUserOrganizationId();
        return userService.partiallyUpdateUser(organizationId, userId, request);
    }

    @PatchMapping("{userId}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeUserStatus(@PathVariable long userId, @RequestBody @Valid UserChangeStatusRequest request)
            throws UnableToDisableCurrentUserException, UserNotFoundException {
        userService.changeUserStatus(securityService.getUserOrganizationId(), securityService.getUserId(),
                userId, request.getStatus());
    }

    @PatchMapping("{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request)
            throws UserNotFoundException, IncorrectPasswordException {
        userService.changePassword(securityService.getUserOrganizationId(), securityService.getUserId(),
                request.getCurrentPassword(), request.getNewPassword());
    }


}
