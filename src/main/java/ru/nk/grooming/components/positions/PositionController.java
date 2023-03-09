package ru.nk.grooming.components.positions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

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
        ResponseWithStatus<PositionEntity> response = positionService.findById(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody PositionEntity position,
            @NonNull HttpServletRequest request
    ) {
        StatusCode response = positionService.save(position, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
