package ru.nk.grooming.components.salons;

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
public class SalonService {
    private final SalonRepo salonRepo;
    private final ServiceFunctions functions;
    private final EmployeeRepo employeeRepo;

    public ResponseWithStatus<SalonEntity> findById(Long id) {
        return functions.findBy(id, salonRepo::findById);
    }
    public List<SalonEntity> findAll() {
        return salonRepo.findAll();
    }
    public List<SalonEntity> findAllByPhone(String phone) {
        return salonRepo.findAllByPhone(phone);
    }
    public StatusCode save(SalonEntity salon, HttpServletRequest request) {
        return functions.saveWithAuth(
                salon,
                salon.getAddress(),
                salonRepo::findByAddress,
                salonRepo::save,
                request
        );
    }
    public StatusCode change(SalonEntity salon, HttpServletRequest request) {
        return functions.changeWithAuth(
                salon,
                salonRepo::findByAddress,
                salon.getAddress(),
                salonRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByIdWithJoinWithAuth(
                id,
                salonRepo::findById,
                salonRepo::deleteById,
                employeeRepo::deleteAllByPositionId,
                request
        );
    }
}
