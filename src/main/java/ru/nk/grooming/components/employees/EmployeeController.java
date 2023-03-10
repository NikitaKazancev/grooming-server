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

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ControllerFunctions functions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<EmployeeEntity>>> findAll(@NonNull HttpServletRequest request) {
        return functions.responseWithStatus(request, employeeService::findAll);
    }
    @GetMapping(params = "positionId")
    public ResponseEntity<ResponseWithStatus<List<EmployeeEntity>>> findAllByPositionId(
            @RequestParam Long positionId,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(positionId, employeeService::findAllByPositionId, request);
    }
    @GetMapping(params = "salonId")
    public ResponseEntity<ResponseWithStatus<List<EmployeeEntity>>> findAllBySalonId(
            @RequestParam Long salonId,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(salonId, employeeService::findAllBySalonId, request);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<EmployeeFullData>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(id, employeeService::findById, request);
    }
    @GetMapping(params = "name")
    public ResponseEntity<ResponseWithStatus<EmployeeFullData>> findByName(
            @RequestParam String name,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(name, employeeService::findByName, request);
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
            @RequestParam Long salonId,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(salonId, employeeService::deleteAllBySalonId, request);
    }
    @DeleteMapping(params = "positionId")
    public ResponseEntity<StatusCode> deleteAllByPositionId(
            @RequestParam Long positionId,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(positionId, employeeService::deleteAllByPositionId, request);
    }
}
