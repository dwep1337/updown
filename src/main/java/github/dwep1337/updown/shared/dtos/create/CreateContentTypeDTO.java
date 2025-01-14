package github.dwep1337.updown.shared.dtos.create;

import jakarta.validation.constraints.NotNull;

public record CreateContentTypeDTO(
        @NotNull(message = "Content type is required")
        String contentType) {
}
