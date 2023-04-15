package ru.nk.grooming.components.positions;

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
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<PositionEntity>>> findAll(
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(request, positionService::findAll);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCode> deleteById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, positionService::deleteById, request);
    }
}
