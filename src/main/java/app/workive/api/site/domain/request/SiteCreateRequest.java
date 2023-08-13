package app.workive.api.site.domain.request;

import app.workive.api.site.domain.entity.Address;
import app.workive.api.site.domain.entity.Location;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class SiteCreateRequest {
    @NotBlank(message = "site.name.blank")
    @Size(min = 1, max = 50, message = "site.name.size")
    private String name;

    @NotBlank(message = "site.country.blank")
    @Size(min = 2, max = 2, message = "site.country.size")
    private String countryCode;

    private Address address;

    private Location location;


}
