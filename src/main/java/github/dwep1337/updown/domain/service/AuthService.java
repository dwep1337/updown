package github.dwep1337.updown.domain.service;

import github.dwep1337.updown.config.DotEnvConfig;
import github.dwep1337.updown.exception.UsernameOrPasswordIncorrectException;
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

    private static final Map<String, String> user = Map.of("username", DotEnvConfig.dotenv().get("AUTH_USERNAME"),
            "password", DotEnvConfig.dotenv().get("AUTH_PASSWORD"));

    public ResponseEntity<AuthLoginResponseDTO> authenticate(@Valid AuthLoginRequestDTO loginRequest) {

        if (user.isEmpty()) {
            throw new UsernameOrPasswordIncorrectException("Username or password is incorrect");
        }

        if (loginRequest.username().equals(user.get("username")) &&
                comparePassword(loginRequest.password(), user.get("password"))) {
            return ResponseEntity.ok().body(new AuthLoginResponseDTO("Login successful", DotEnvConfig.dotenv().get("AUTH_TOKEN")));
        }

        throw new UsernameOrPasswordIncorrectException("Username or password is incorrect");
    }

    private boolean comparePassword(String loginPassword, String userPassword) {
        return loginPassword.equals(userPassword);
    }
}
