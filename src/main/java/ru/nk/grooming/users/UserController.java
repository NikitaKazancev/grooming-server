package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ControllerFunctions functions;

    @PutMapping
    public ResponseEntity<StatusCode> change(
            @RequestBody User user,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(user, userService::change, request);
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

    @GetMapping
    public ResponseEntity<Iterable<User>> findAll(
            @NonNull HttpServletRequest request
    ) {
        Iterable<User> response = userService.findAll(request);
        if (response == null) {
            return ResponseEntity.status(403).body(null);
        }

        return ResponseEntity.ok(userService.findAll(request));
    }

    @DeleteMapping
    public ResponseEntity<StatusCode> deleteById(@NonNull HttpServletRequest request) {
        StatusCode response = userService.deleteById(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
