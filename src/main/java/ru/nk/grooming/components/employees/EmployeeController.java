package ru.nk.grooming.components.employees;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.components.employees.dto.EmployeeFullData;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Iterable<EmployeeEntity>> findAll(@NonNull HttpServletRequest request) {
        return ResponseEntity.ok(employeeService.findAll(request));
    }
    @GetMapping(params = "name")
    public ResponseEntity<ResponseWithStatus<EmployeeFullData>> findByName(
            @RequestParam String name,
            @NonNull HttpServletRequest request
    ) {
        ResponseWithStatus<EmployeeFullData> response = employeeService.findByName(name, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody EmployeeEntity employee,
            @NonNull HttpServletRequest request
    ) {
        StatusCode response = employeeService.save(employee, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
