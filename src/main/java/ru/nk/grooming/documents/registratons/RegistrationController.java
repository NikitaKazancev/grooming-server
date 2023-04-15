package ru.nk.grooming.documents.registratons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.documents.registratons.dto.RegistrationFullData;
import ru.nk.grooming.documents.registratons.dto.RegistrationRequest;
import ru.nk.grooming.general.requests.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final ControllerFunctions functions;
    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<RegistrationEntity>>> findAll(
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(request, registrationService::findAll);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<RegistrationFullData>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(id, registrationService::findById, request);
    }
    @GetMapping(params = "salonId")
    public ResponseEntity<ResponseWithStatus<RegistrationFullData>> findBySalonId(
            @RequestParam Long salonId,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(salonId, registrationService::findBySalonId, request);
    }
    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody RegistrationRequest registration,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(registration, registrationService::save, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCode> deleteById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, registrationService::deleteById, request);
    }
}
