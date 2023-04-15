package ru.nk.grooming.components.employees;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.components.employees.dto.EmployeeFullData;
import ru.nk.grooming.components.positions.PositionEntity;
import ru.nk.grooming.components.positions.PositionRepo;
import ru.nk.grooming.components.salons.SalonEntity;
import ru.nk.grooming.components.salons.SalonRepo;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final ServiceFunctions functions;
    private final PositionRepo positionRepo;
    private final SalonRepo salonRepo;

    private EmployeeFullData fullData(List<Object[]> entitiesArr) {
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
        if (!positionRepo.existsById(employee.getPositionId())) {
            return true;
        }
        if (!salonRepo.existsById(employee.getSalonId())) {
            return true;
        }

        return false;
    }

    public ResponseWithStatus<List<EmployeeEntity>> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(employeeRepo::findAll, request);
    }
    public ResponseWithStatus<List<EmployeeEntity>> findAllByPositionId(Long positionId, HttpServletRequest request) {
        return functions.findAllByWithAuth(positionId, employeeRepo::findAllByPositionId, request);
    }
    public ResponseWithStatus<List<EmployeeEntity>> findAllBySalonId(Long salonId, HttpServletRequest request) {
        return functions.findAllByWithAuth(salonId, employeeRepo::findAllBySalonId, request);
    }
    public ResponseWithStatus<EmployeeFullData> findById(Long id, HttpServletRequest request) {
        return functions.findByWithJoinWithAuth(
                id,
                employeeRepo::findByIdWithJoin,
                this::fullData,
                request
        );
    }
    public ResponseWithStatus<EmployeeFullData> findByName(
            String name,
            HttpServletRequest request
    ) {
        return functions.findByWithJoinWithAuth(
                name,
                employeeRepo::findByNameWithJoin,
                this::fullData,
                request
        );
    }
    public StatusCode save(EmployeeEntity employee, HttpServletRequest request) {
        return functions.saveWithCheckFieldsWithAuth(
                employee,
                this::fieldsNotExist,
                employee.getName(),
                employeeRepo::findByName,
                employeeRepo::save,
                request
        );
    }
    public StatusCode change(EmployeeEntity employee, HttpServletRequest request) {
        return functions.changeWithCheckFieldsWithAuth(
                employee,
                this::fieldsNotExist,
                employeeRepo::findByName,
                employee.getName(),
                employeeRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByWithAuth(
                id,
                employeeRepo::findById,
                employeeRepo::deleteById,
                request
        );
    }
    public StatusCode deleteAllBySalonId(Long salonId, HttpServletRequest request) {
        return functions.deleteAllByWithAuth(
                salonId,
                employeeRepo::deleteAllBySalonId,
                request
        );
    }
    public StatusCode deleteAllByPositionId(Long positionId, HttpServletRequest request) {
        return functions.deleteAllByWithAuth(
                positionId,
                employeeRepo::deleteAllByPositionId,
                request
        );
    }
}
