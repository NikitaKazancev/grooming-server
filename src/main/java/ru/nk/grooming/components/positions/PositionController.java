package ru.nk.grooming.components.positions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<Iterable<PositionEntity>> findAll(
            @NonNull HttpServletRequest request
    ) {
        return ResponseEntity.ok(positionService.findAll(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<PositionEntity>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(id, positionService::findById, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody PositionEntity position,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(position, positionService::save, request);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<StatusCode> deleteById(
            @RequestParam String name,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(name, positionService::deleteByName, request);
    }
}
