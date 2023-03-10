package ru.nk.grooming.components.salons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.general.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepo salonRepo;
    private final ServiceFunctions functions;

    public StatusCode save(SalonEntity salonData, HttpServletRequest request) {
        return functions.saveWithAuth(
                salonData,
                salonData.getAddress(),
                salonRepo::findByAddress,
                salonRepo::save,
                request
        );
    }

    public ResponseWithStatus<SalonEntity> findById(Long id) {
        return functions.findBy(id, salonRepo::findById);
    }

    public Iterable<SalonEntity> findAll() {
        return salonRepo.findAll();
    }

    public Iterable<SalonEntity> findAllByPhone(String phone) {
        return salonRepo.findAllByPhone(phone);
    }
}
