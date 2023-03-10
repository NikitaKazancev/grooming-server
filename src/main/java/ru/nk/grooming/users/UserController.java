package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public ResponseEntity<StatusCode> change(
            @RequestBody User user,
            @NonNull HttpServletRequest request
    ) {
        StatusCode response = userService.change(user, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<User>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        ResponseWithStatus<User> response = userService.findById(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping(params = "email")
    public ResponseEntity<ResponseWithStatus<User>> findByEmail(
            @RequestParam String email,
            @NonNull HttpServletRequest request
    ) {
        ResponseWithStatus<User> response = userService.findByEmail(email, request);
        return ResponseEntity.status(response.getStatus()).body(response);
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
