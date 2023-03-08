package ru.nk.grooming.salons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.salons.dto.SalonResponse;
import ru.nk.grooming.types.StatusCode;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonService {
    private final SalonRepo salonRepo;

    public StatusCode save(SalonEntity salonData) {
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
