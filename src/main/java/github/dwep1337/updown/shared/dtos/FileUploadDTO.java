package github.dwep1337.updown.shared.dtos;

import github.dwep1337.updown.shared.validation.IsAllowedContentType;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record FileUploadDTO(
    @NotNull(message = "File is required")
    @IsAllowedContentType
    MultipartFile file
) {
}