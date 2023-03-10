package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.dto.UserDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AuthService authService;
    private final ServiceFunctions functions;

    public ResponseWithStatus<UserDTO> findById(Long id, HttpServletRequest request) {
        ResponseWithStatus<User> response = functions.findByWithAuth(id, userRepo::findById, request);
        return ResponseWithStatus.create(
                response.getStatus(),
                UserDTO.create(response.getData())
        );
    }
    public ResponseWithStatus<UserDTO> findByEmail(String email, HttpServletRequest request) {
        ResponseWithStatus<User> response = functions.findByWithAuth(email, userRepo::findByEmail, request);
        return ResponseWithStatus.create(
                response.getStatus(),
                UserDTO.create(response.getData())
        );
    }
    public ResponseWithStatus<List<UserDTO>> findAll(HttpServletRequest request) {
        ResponseWithStatus<List<User>> response = functions.findAllWithAuth(userRepo::findAll, request);
        return ResponseWithStatus.create(
                response.getStatus(),
                response.getData().stream().map(UserDTO::create).toList()
        );
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
