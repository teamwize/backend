package app.workive.api.site.domain.response;


import lombok.Data;

@Data
public class SiteResponse {
    private long id;
    private String name;
    private boolean isDefault;
    private String country;
    private String language;

    private String timezone;
}
