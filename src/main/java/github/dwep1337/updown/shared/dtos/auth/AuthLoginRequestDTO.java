package github.dwep1337.updown.shared.dtos.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthLoginRequestDTO(
        @NotEmpty(message = "Username is required")
        String username,
        @NotEmpty(message = "Password is required")
        String password) {
}
