package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.requests.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<List<SalonEntity>> findAll() {
        return ResponseEntity.ok(salonService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<SalonEntity>> findById(
            @PathVariable Long id
    ) {
        return functions.responseWithStatus(id, salonService::findById);
    }
    @GetMapping(params = "phone")
    public ResponseEntity<List<SalonEntity>> findAllByPhone(
            @RequestParam String phone
    ) {
        return ResponseEntity.ok(salonService.findAllByPhone(phone));
    }
    @PutMapping
    public ResponseEntity<StatusCode> change(
            @RequestBody SalonEntity salon,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(salon, salonService::change, request);
    }
    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody SalonEntity salon,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(salon, salonService::save, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCode> deleteById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, salonService::deleteById, request);
    }
}
