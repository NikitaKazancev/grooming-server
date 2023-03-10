package ru.nk.grooming.components.employees;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.components.employees.dto.EmployeeFullData;
import ru.nk.grooming.general.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<Iterable<EmployeeEntity>> findAll(@NonNull HttpServletRequest request) {
        return ResponseEntity.ok(employeeService.findAll(request));
    }
    @GetMapping(params = "name")
    public ResponseEntity<ResponseWithStatus<EmployeeFullData>> findByName(
            @RequestParam String name,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(name, employeeService::findByName, request);
    }

    @GetMapping(params = "positionId")
    public ResponseEntity<Iterable<EmployeeEntity>> findAllByPositionId(
            @RequestParam Long positionId,
            @NonNull HttpServletRequest request
    ) {
        return ResponseEntity.ok(employeeService.findAllByPositionId(positionId, request));
    }

    @GetMapping(params = "salonId")
    public ResponseEntity<Iterable<EmployeeEntity>> findAllBySalonId(
            @RequestParam Long salonId,
            @NonNull HttpServletRequest request
    ) {
        return ResponseEntity.ok(employeeService.findAllBySalonId(salonId, request));
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody EmployeeEntity employee,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(employee, employeeService::save, request);
    }

    @PutMapping
    public ResponseEntity<StatusCode> change(
            @RequestBody EmployeeEntity employee,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(employee, employeeService::change, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCode> deleteById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, employeeService::deleteById, request);
    }

    @DeleteMapping(params = "salonId")
    public ResponseEntity<StatusCode> deleteAllBySalonId(
            @RequestParam Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, employeeService::deleteAllBySalonId, request);
    }

    @DeleteMapping(params = "positionId")
    public ResponseEntity<StatusCode> deleteAllByPositionId(
            @RequestParam Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, employeeService::deleteAllByPositionId, request);
    }
}
