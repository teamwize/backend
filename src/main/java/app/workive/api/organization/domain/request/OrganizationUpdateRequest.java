package app.workive.api.organization.domain.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class OrganizationUpdateRequest {
    @NotBlank(message = "organization.name.blank")
    @Size(min = 3, max = 100, message = "organization.name.size")
    String name;
}
