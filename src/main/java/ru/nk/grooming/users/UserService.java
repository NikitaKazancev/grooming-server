package ru.nk.grooming.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.components.employees.EmployeeEntity;
import ru.nk.grooming.components.products.ProductEntity;
import ru.nk.grooming.components.salons.SalonEntity;
import ru.nk.grooming.documents.registratons.RegistrationEntity;
import ru.nk.grooming.documents.registratons.RegistrationRepo;
import ru.nk.grooming.documents.registratons.dto.RegistrationFullData;
import ru.nk.grooming.general.requests.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.dto.UserDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AuthService authService;
    private final ServiceFunctions functions;
    private final RegistrationRepo registrationRepo;
    private final PasswordEncoder passwordEncoder;

    private RegistrationFullData getRegistrations(Object[] entities) {
        RegistrationEntity registration = (RegistrationEntity) entities[0];
        SalonEntity salon = (SalonEntity) entities[1];
        EmployeeEntity employee = (EmployeeEntity) entities[2];
        ProductEntity product = (ProductEntity) entities[3];

        return RegistrationFullData.builder()
                .id(registration.getId())
                .date(registration.getDate())
                .duration(registration.getDuration())
                .price(registration.getPrice())
                .salon(salon)
                .employee(employee)
                .product(product)
                .comment(registration.getComment())
                .build();
    }

    public ResponseWithStatus<UserDTO> findById(Long id, HttpServletRequest request) {
        ResponseWithStatus<User> response = functions.findByWithAuth(id, userRepo::findById, request);
        System.out.println(registrationRepo.findAllByUserIdWithJoinWithoutUser(id));
        List<RegistrationFullData> registrations = registrationRepo.
                findAllByUserIdWithJoinWithoutUser(id).stream()
                .map(this::getRegistrations)
                .toList();
        return ResponseWithStatus.create(
                response.getStatus(),
                UserDTO.create(response.getData(), registrations)
        );
    }
    public ResponseWithStatus<UserDTO> findByEmail(String email, HttpServletRequest request) {
        ResponseWithStatus<User> response = functions.findByWithAuth(email, userRepo::findByEmail, request);
        if (response.getData() != null) {
            Long id = response.getData().getId();
            System.out.println(registrationRepo.findAllByUserIdWithJoinWithoutUser(id));
            List<RegistrationFullData> registrations = registrationRepo.
                    findAllByUserIdWithJoinWithoutUser(id).stream()
                    .map(this::getRegistrations)
                    .toList();

            return ResponseWithStatus.create(
                    response.getStatus(),
                    UserDTO.create(response.getData(), registrations)
            );
        }

        return ResponseWithStatus.create(
                response.getStatus(),
                UserDTO.create(null)
        );
    }
    public ResponseWithStatus<List<UserDTO>> findAll(HttpServletRequest request) {
        ResponseWithStatus<List<User>> response = functions.findAllWithAuth(userRepo::findAll, request);
        return ResponseWithStatus.create(
                response.getStatus(),
                response.getData().stream().map(UserDTO::create).toList()
        );
    }
    public StatusCode save(User user, HttpServletRequest request) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return functions.saveWithAuth(
                user,
                user.getEmail(),
                userRepo::findByEmail,
                userRepo::save,
                request
        );
    }
    public StatusCode change(User user, HttpServletRequest request) {
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        dbUser.merge(user);
        userRepo.save(dbUser);
        return StatusCode.create(200);
    }
    public StatusCode deleteById(HttpServletRequest request) {
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        userRepo.deleteById(dbUser.getId());
        return StatusCode.create(200);
    }
}
