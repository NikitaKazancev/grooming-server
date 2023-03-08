package ru.nk.grooming.components.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.jwt.JwtService;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;
import ru.nk.grooming.users.UserRepo;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AuthService authService;
    public int home(HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return 403;
        }

        return 200;
    }
}
