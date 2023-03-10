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
import ru.nk.grooming.general.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ServiceFunctions functions;
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

    public boolean fieldsNotExist(EmployeeEntity employee) {
        PositionEntity position = positionRepo.findById(employee.getPositionId()).orElse(null);
        if (position == null) {
            return true;
        }
        SalonEntity salon = salonRepo.findById(employee.getSalonId()).orElse(null);
        if (salon == null) {
            return true;
        }

        return false;
    }

    public ResponseWithStatus<EmployeeFullData> findByName(
            String name,
            HttpServletRequest request
    ) {
        return functions.findByWithJoinWithAuth(
                name,
                employeeRepo::findByNameWithJoin,
                this::employeeFullData,
                request
        );
    }

    public Iterable<EmployeeEntity> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(employeeRepo::findAll, request);
    }

    public Iterable<EmployeeEntity> findAllByPositionId(Long positionId, HttpServletRequest request) {
        return functions.findAllByWithAuth(positionId, employeeRepo::findAllByPositionId, request);
    }

    public Iterable<EmployeeEntity> findAllBySalonId(Long salonId, HttpServletRequest request) {
        return functions.findAllByWithAuth(salonId, employeeRepo::findAllBySalonId, request);
    }

    public StatusCode save(EmployeeEntity employee, HttpServletRequest request) {
        return save(employee, request, false);
    }

    public StatusCode change(EmployeeEntity employee, HttpServletRequest request) {
        return save(employee, request, true);
    }

    public StatusCode save(EmployeeEntity employee, HttpServletRequest request, boolean putReq) {
        if (authService.isNotAdmin(request)) {
            return StatusCode.create(403);
        }

        if (fieldsNotExist(employee)) {
            return StatusCode.create(404);
        }

        EmployeeEntity dbEmployee = employeeRepo.findByName(employee.getName()).orElse(null);
        if (dbEmployee == null) {
            return StatusCode.create(404);
        }

        if (putReq) {
            dbEmployee.merge(employee);
            employeeRepo.save(dbEmployee);
        } else {
            employeeRepo.save(employee);
        }

        return StatusCode.create(200);
    }

    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByWithAuth(
                id,
                employeeRepo::findById,
                () -> employeeRepo.deleteById(id),
                request
        );
    }

    public StatusCode deleteAllBySalonId(Long salonId, HttpServletRequest request) {
        return functions.deleteAllByWithAuth(
                () -> employeeRepo.deleteAllBySalonId(salonId),
                request
        );
    }

    public StatusCode deleteAllByPositionId(Long positionId, HttpServletRequest request) {
        return functions.deleteAllByWithAuth(
                () -> employeeRepo.deleteAllByPositionId(positionId),
                request
        );
    }
}
