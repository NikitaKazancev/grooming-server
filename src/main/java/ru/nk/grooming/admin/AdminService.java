package ru.nk.grooming.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.jwt.JwtService;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;
import ru.nk.grooming.users.UserRepo;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    public int home(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        User user = userRepo
                .findByEmail(jwtService.getEmail(jwt))
                .orElseThrow();

        if (user.getRole() == Role.ADMIN) {
            return 200;
        }

        return 403;
    }
}
