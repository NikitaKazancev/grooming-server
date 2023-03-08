package ru.nk.grooming.components.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<Integer> home(
            @NonNull HttpServletRequest request
    ) {
        int statusCode = adminService.home(request);
        return ResponseEntity.status(statusCode).body(statusCode);
    }
}
