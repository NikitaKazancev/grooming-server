package ru.nk.grooming.components.employees;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.components.employees.dto.EmployeeFullData;
import ru.nk.grooming.components.positions.PositionEntity;
import ru.nk.grooming.components.positions.PositionRepo;
import ru.nk.grooming.components.salons.SalonEntity;
import ru.nk.grooming.components.salons.SalonRepo;
import ru.nk.grooming.general.RequestFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RequestFunctions requestFunctions;
    private final AuthService authService;
    private final PositionRepo positionRepo;
    private final SalonRepo salonRepo;

    private EmployeeFullData employeeFullData(List<Object[]> entitiesArr) {
        Object[] entities = entitiesArr.get(0);
        EmployeeEntity employee = (EmployeeEntity) entities[0];
        SalonEntity salon = (SalonEntity) entities[1];
        PositionEntity position = (PositionEntity) entities[2];

        return EmployeeFullData.builder()
                .id(employee.getId())
                .name(employee.getName())
                .phone(employee.getPhone())
                .address(employee.getAddress())
                .salon(salon)
                .position(position)
                .build();
    }

    public ResponseWithStatus<EmployeeFullData> findByName(
            String name,
            HttpServletRequest request
    ) {
        if (authService.isNotAdmin(request)) {
            return ResponseWithStatus.empty(403);
        }

        List<Object[]> response = employeeRepo.findByName(name);
        if (response.size() != 1) {
            return ResponseWithStatus.empty(404);
        }

        return ResponseWithStatus.create(200, employeeFullData(response));
    }

    public Iterable<EmployeeEntity> findAll(HttpServletRequest request) {
        return requestFunctions.findAllWithAuth(employeeRepo::findAll, request);
    }

    public StatusCode save(
            EmployeeEntity employee,
            HttpServletRequest request
    ) {
        ResponseWithStatus<EmployeeFullData> dbEmployee = findByName(employee.getName(), request);
        if (dbEmployee.getStatus() == 200) {
            return StatusCode.create(409);
        }

        PositionEntity position = positionRepo.findById(employee.getPositionId()).orElse(null);
        if (position == null) {
            return StatusCode.create(404);
        }
        SalonEntity salon = salonRepo.findById(employee.getSalonId()).orElse(null);
        if (salon == null) {
            return StatusCode.create(404);
        }

        employeeRepo.save(employee);
        return StatusCode.create(200);
    }
}
