package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<User>>> findAll(
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(request, userService::findAll);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<User>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(id, userService::findById, request);
    }
    @GetMapping(params = "email")
    public ResponseEntity<ResponseWithStatus<User>> findByEmail(
            @RequestParam String email,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(email, userService::findByEmail, request);
    }
    @PutMapping
    public ResponseEntity<StatusCode> change(
            @RequestBody User user,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(user, userService::change, request);
    }
    @DeleteMapping
    public ResponseEntity<StatusCode> deleteById(@NonNull HttpServletRequest request) {
        StatusCode response = userService.deleteById(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
