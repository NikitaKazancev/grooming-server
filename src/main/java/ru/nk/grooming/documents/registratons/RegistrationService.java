package ru.nk.grooming.documents.registratons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.components.employees.EmployeeEntity;
import ru.nk.grooming.components.employees.EmployeeRepo;
import ru.nk.grooming.components.products.ProductEntity;
import ru.nk.grooming.components.products.ProductRepo;
import ru.nk.grooming.components.salons.SalonEntity;
import ru.nk.grooming.components.salons.SalonRepo;
import ru.nk.grooming.documents.registratons.dto.RegistrationFullData;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.User;
import ru.nk.grooming.users.UserRepo;
import ru.nk.grooming.users.dto.UserDTO;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ServiceFunctions functions;
    private final RegistrationRepo registrationRepo;
    private final SalonRepo salonRepo;
    private final EmployeeRepo employeeRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    private RegistrationFullData fullData(List<Object[]> entitiesArr) {
        Object[] entities = entitiesArr.get(0);
        RegistrationEntity registration = (RegistrationEntity) entities[0];
        SalonEntity salon = (SalonEntity) entities[1];
        EmployeeEntity employee = (EmployeeEntity) entities[2];
        ProductEntity product = (ProductEntity) entities[3];
        UserDTO user = UserDTO.create((User) entities[4]);

        return RegistrationFullData.builder()
                .id(registration.getId())
                .date(registration.getDate())
                .duration(registration.getDuration())
                .price(registration.getPrice())
                .salon(salon)
                .employee(employee)
                .product(product)
                .user(user)
                .comment(registration.getComment())
                .build();
    }
    public boolean fieldsNotExist(RegistrationEntity registration) {
        SalonEntity salon = salonRepo.findById(registration.getSalonId()).orElse(null);
        if (salon == null) {
            return true;
        }
        EmployeeEntity employee = employeeRepo.findById(registration.getEmployeeId()).orElse(null);
        if (employee == null) {
            return true;
        }
        ProductEntity product = productRepo.findById(registration.getProductId()).orElse(null);
        if (product == null) {
            return true;
        }
        User user = userRepo.findById(registration.getUserId()).orElse(null);
        if (user == null) {
            return true;
        }

        return false;
    }

    public ResponseWithStatus<List<RegistrationEntity>> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(registrationRepo::findAll, request);
    }
    public ResponseWithStatus<RegistrationFullData> findById(Long id, HttpServletRequest request) {
        return functions.findByWithJoinWithAuth(
                id,
                registrationRepo::findByIdWithJoin,
                this::fullData,
                request
        );
    }
    public ResponseWithStatus<RegistrationFullData> findBySalonId(Long salonId, HttpServletRequest request) {
        return functions.findByWithJoinWithAuth(
                salonId,
                registrationRepo::findBySalonIdWithJoin,
                this::fullData,
                request
        );
    }
    public StatusCode save(RegistrationEntity registration, HttpServletRequest request) {
        registration.setDate(new Date());

        return functions.saveWithCheckFieldsWithAuth(
                registration,
                this::fieldsNotExist,
                registration.getId(),
                registrationRepo::findById,
                registrationRepo::save,
                request
        );
    }
    public StatusCode deleteById(Long id, HttpServletRequest request) {
        return functions.deleteByWithAuth(
                id,
                registrationRepo::findById,
                registrationRepo::deleteById,
                request
        );
    }
}
