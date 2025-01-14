package github.dwep1337.updown.controller;

import github.dwep1337.updown.domain.service.AuthService;
import github.dwep1337.updown.shared.dtos.auth.AuthLoginRequestDTO;
import github.dwep1337.updown.shared.dtos.auth.AuthLoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> authenticate(@Valid @RequestBody AuthLoginRequestDTO loginRequest) {
        return authService.authenticate(loginRequest);
    }

}
