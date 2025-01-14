package github.dwep1337.updown.domain.service;

import github.dwep1337.updown.config.DotEnvConfig;
import github.dwep1337.updown.exception.UsernameOrPasswordIncorrectException;
import github.dwep1337.updown.security.TokenService;
import github.dwep1337.updown.shared.dtos.auth.AuthLoginRequestDTO;
import github.dwep1337.updown.shared.dtos.auth.AuthLoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenService tokenService;


    private static final Map<String, String> USER = Map.of("username", DotEnvConfig.dotenv().get("AUTH_USERNAME"),
            "password", DotEnvConfig.dotenv().get("AUTH_PASSWORD"));

    public ResponseEntity<AuthLoginResponseDTO> authenticate(@Valid AuthLoginRequestDTO loginRequest) {

        if (USER.isEmpty()) {
            throw new UsernameOrPasswordIncorrectException("Username or password is incorrect");
        }

        if (authenticateUser(loginRequest)) {
            return ResponseEntity.ok().body(new AuthLoginResponseDTO(loginRequest.username(),
                    "Login successful", tokenService.generateToken(loginRequest.username())));
        }

        throw new UsernameOrPasswordIncorrectException("Username or password is incorrect");
    }


    private boolean authenticateUser(AuthLoginRequestDTO loginRequest) {
        return USER.get("username").equals(loginRequest.username())
                && USER.get("password").equals(loginRequest.password());
    }
}
