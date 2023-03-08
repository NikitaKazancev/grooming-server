package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.components.salons.dto.SalonResponse;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepo salonRepo;
    private final AuthService authService;

    public StatusCode save(SalonEntity salonData, HttpServletRequest request) {
        User user = authService.getUserByHttpRequest(request);

        if (user.getRole() != Role.ADMIN) {
            return new StatusCode(403);
        }

        SalonEntity salon = findByAddress(salonData.getAddress());
        if (salon != null) {
            return new StatusCode(409);
        }

        salonRepo.save(salonData);
        return new StatusCode(200);
    }

    public SalonResponse findById(Long id) {
        Optional<SalonEntity> salon = salonRepo.findById(id);
        if (salon.isPresent()) {
            return SalonResponse.builder()
                    .data(salon.get())
                    .statusCode(200)
                    .build();
        }

        return SalonResponse.builder()
                .statusCode(404)
                .data(null)
                .build();
    }

    public SalonEntity findByAddress(String address) {
        return salonRepo.findByAddress(address).orElse(null);
    }

    public Iterable<SalonEntity> findAll() {
        return salonRepo.findAll();
    }

    public Iterable<SalonEntity> findAllByPhone(String phone) {
        return salonRepo.findAllByPhone(phone);
    }
}
