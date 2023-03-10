package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.general.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AuthService authService;
    private final ServiceFunctions functions;

    public ResponseWithStatus<User> findById(Long id, HttpServletRequest request) {
        return functions.findByWithAuth(id, userRepo::findById, request);
    }
    public ResponseWithStatus<User> findByEmail(String email, HttpServletRequest request) {
        return functions.findByWithAuth(email, userRepo::findByEmail, request);
    }
    public ResponseWithStatus<List<User>> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(userRepo::findAll, request);
    }
    public StatusCode change(User user, HttpServletRequest request) {
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        dbUser.merge(user);
        userRepo.save(dbUser);
        return StatusCode.create(200);
    }
    public StatusCode deleteById(HttpServletRequest request) {
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        userRepo.deleteById(dbUser.getId());
        return StatusCode.create(200);
    }
}
