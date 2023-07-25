package app.workive.api.base.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.annotation.Nonnull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    @Nonnull
    Integer pageNumber;
    @Nonnull
    Integer pageSize;
    @Nonnull
    Integer totalPages;
    @Nonnull
    Long totalContents;
}
