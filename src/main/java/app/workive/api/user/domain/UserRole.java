package app.workive.api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ORGANIZATION_ADMIN("organization_admin"),
    SITE_ADMIN("site_admin"),
    EMPLOYEE("employee"),
    API("api");

    private final String name;
}