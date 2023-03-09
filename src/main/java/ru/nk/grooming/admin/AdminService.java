package ru.nk.grooming.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.authentication.routes.dto.AuthResponseDTO;
import ru.nk.grooming.authentication.routes.dto.RegisterRequestDTO;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.User;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthService authService;
    public StatusCode home(HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return StatusCode.create(403);
        }

        return StatusCode.create(200);
    }

    public AuthResponseDTO saveUser(User user, HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return AuthResponseDTO.builder()
                    .jwt(null)
                    .status(403)
                    .build();
        }

        return authService.register(
                RegisterRequestDTO.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .build(), user.getRole()
        );
    }
}
