package github.dwep1337.updown.shared.dtos;

import org.springframework.web.multipart.MultipartFile;

import github.dwep1337.updown.shared.validation.IsAllowedContentType;
import jakarta.validation.constraints.NotNull;

public record FileUploadDTO(
    @NotNull
    @IsAllowedContentType
    MultipartFile file
) {
}