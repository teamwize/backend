package app.workive.api.site.domain.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@Data
@NoArgsConstructor
public class SiteCompactResponse {
    @Nonnull
    private Long id;
    @Nonnull
    private String name;
    @Nonnull
    private boolean isDefault;
    @Nonnull
    private String country;
    @Nullable
    private String language;
    @Nullable
    private String timezone;
}
