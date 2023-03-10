package ru.nk.grooming.documents.salary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nk.grooming.documents.salary.dto.SalaryFullData;
import ru.nk.grooming.general.requests.ControllerFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final ControllerFunctions functions;
    private final SalaryService salaryService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<SalaryEntity>>> findAll(
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(request, salaryService::findAll);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWithStatus<SalaryFullData>> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.responseWithStatus(id, salaryService::findById, request);
    }
    @PostMapping
    public ResponseEntity<StatusCode> save(
            @RequestBody SalaryEntity salary,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(salary, salaryService::save, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusCode> deleteById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
        return functions.statusCode(id, salaryService::deleteById, request);
    }
}
