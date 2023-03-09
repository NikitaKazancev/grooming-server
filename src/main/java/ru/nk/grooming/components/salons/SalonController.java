package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;
    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody SalonEntity salonData,
            @NonNull HttpServletRequest request
    ) {
        StatusCode statusCode = salonService.save(salonData, request);
        return ResponseEntity.status(statusCode.getStatusCode()).body(statusCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<SalonEntity>> findById(
            @PathVariable Long id
    ) {
        ResponseWithStatus<SalonEntity> response = salonService.findById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping
    public ResponseEntity<Iterable<SalonEntity>> findAll() {
        return ResponseEntity.ok(salonService.findAll());
    }

    @GetMapping(params = "phone")
    public ResponseEntity<Iterable<SalonEntity>> findAllByPhone(
            @RequestParam String phone
    ) {
        return ResponseEntity.ok(salonService.findAllByPhone(phone));
    }
}
