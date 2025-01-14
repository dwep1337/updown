package github.dwep1337.updown.shared.dtos.auth;

public record AuthLoginResponseDTO(
        String username,
        String message,
        String accessToken
) {
}
