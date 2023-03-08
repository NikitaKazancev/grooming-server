package ru.nk.grooming.admin;

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
        User user = authService.getUserByHttpRequest(request);

        if (user != null && user.getRole() == Role.ADMIN) {
            return 200;
        }

        return 403;
    }
}
