package ru.nk.grooming.components.positions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.components.employees.EmployeeRepo;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepo positionRepo;
    private final EmployeeRepo employeeRepo;
    private final ServiceFunctions functions;

    public ResponseWithStatus<List<PositionEntity>> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(positionRepo::findAll, request);
    }
    public ResponseWithStatus<PositionEntity> findById(
            Long id,
            HttpServletRequest request
    ) {
        return functions.findByWithAuth(id, positionRepo::findById, request);
    }
    public StatusCode save(
            PositionEntity position,
            HttpServletRequest request
    ) {
        return functions.saveWithAuth(
                position,
                position.getName(),
                positionRepo::findByName,
                positionRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByIdWithJoinWithAuth(
                id,
                positionRepo::findById,
                positionRepo::deleteById,
                employeeRepo::deleteAllByPositionId,
                request
        );
    }
}
