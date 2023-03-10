package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;
    private final ControllerFunctions functions;

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody SalonEntity salonData,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(salonData, salonService::save, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<SalonEntity>> findById(
            @PathVariable Long id
    ) {
        return functions.responseWithStatus(id, salonService::findById);
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
