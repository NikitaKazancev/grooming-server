package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.general.RequestFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepo salonRepo;
    private final AuthService authService;
    private final RequestFunctions requestFunctions;

    public StatusCode save(SalonEntity salonData, HttpServletRequest request) {
        return requestFunctions.save(
                salonData,
                salonData.getAddress(),
                salonRepo::findByAddress,
                salonRepo::save,
                request
        );
    }

    public ResponseWithStatus<SalonEntity> findById(Long id) {
        return requestFunctions.findBy(id, salonRepo::findById);
    }

    public Iterable<SalonEntity> findAll() {
        return salonRepo.findAll();
    }

    public Iterable<SalonEntity> findAllByPhone(String phone) {
        return salonRepo.findAllByPhone(phone);
    }
}
