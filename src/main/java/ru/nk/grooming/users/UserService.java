package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.dto.UserResponse;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AuthService authService;

    public StatusCode change(User user, HttpServletRequest request) {
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        dbUser.mergeUser(user);
        userRepo.save(dbUser);
        return StatusCode.create(200);
    }

    public <T> UserResponse findBy(
            T property,
            Function<T, Optional<User>> findFunction,
            HttpServletRequest request
    ) {
        if (authService.isNotAdmin(request)) {
            return UserResponse.builder()
                    .statusCode(403)
                    .data(null)
                    .build();
        }

        User user = findFunction.apply(property).orElse(null);
        if (user == null) {
            return UserResponse.builder()
                    .statusCode(404)
                    .data(null)
                    .build();
        }

        return UserResponse.builder()
                .statusCode(200)
                .data(user)
                .build();
    }
    public UserResponse findById(Long id, HttpServletRequest request) {
        return findBy(id, userRepo::findById, request);
    }

    public UserResponse findByEmail(String email, HttpServletRequest request) {
        return findBy(email, userRepo::findByEmail, request);
    }

    public Iterable<User> findAll(HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return null;
        }

        return userRepo.findAll();
    }
}
