package app.workive.api.site.domain.request;


import app.workive.api.site.domain.entity.Address;
import app.workive.api.site.domain.entity.Location;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class SiteUpdateRequest {

    @NotBlank(message = "site.name.blank")
    @Size(min = 1, max = 50, message = "site.name.size")
    private String name;

    private Address address;

    private Location location;

    @Size(min = 1, max = 20, message = "site.lang.size")
    private String language;


    @NotEmpty(message = "site.timezone.blank")
    private String timezone;




}
