package ru.nk.grooming.components.positions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.authentication.routes.components.AuthService;
import ru.nk.grooming.general.RequestFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepo positionRepo;
    private final AuthService authService;
    private final RequestFunctions requestFunctions;

    public Iterable<PositionEntity> findAll(HttpServletRequest request) {
        if (authService.isNotAdmin(request)) {
            return null;
        }

        return positionRepo.findAll();
    }

    public StatusCode save(
            PositionEntity position,
            HttpServletRequest request
    ) {
        return requestFunctions.save(
                position,
                position.getName(),
                positionRepo::findByName,
                positionRepo::save,
                request
        );
    }
}
