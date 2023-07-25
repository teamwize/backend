package app.workive.api.base.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.annotation.Nonnull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {
    @Nonnull
    List<T> contents;
    @Nonnull
    PaginationResponse pagination;


    public PagedResponse(List<T> contents, Integer pageNumber, Integer pageSize, Integer totalPages, Long totalContents) {
        this.pagination = new PaginationResponse(pageNumber, pageSize, totalPages, totalContents);
        this.contents = contents;
    }
}