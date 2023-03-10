package ru.nk.grooming.components.positions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nk.grooming.general.ServiceFunctions;
import ru.nk.grooming.types.ResponseWithStatus;
import ru.nk.grooming.types.StatusCode;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepo positionRepo;
    private final ServiceFunctions functions;

    public Iterable<PositionEntity> findAll(HttpServletRequest request) {
        return functions.findAllWithAuth(positionRepo::findAll, request);
    }

    public ResponseWithStatus<PositionEntity> findById(
            Long id,
            HttpServletRequest request
    ) {
        return functions.findByWithAuth(id, positionRepo::findById, request);
    }

    public StatusCode save(
            PositionEntity position,
            HttpServletRequest request
    ) {
        return functions.saveWithAuth(
                position,
                position.getName(),
                positionRepo::findByName,
                positionRepo::save,
                request
        );
    }

    public StatusCode deleteByName(String name, HttpServletRequest request) {
        return functions.deleteByWithAuth(
                name,
                positionRepo::findByName,
                () -> positionRepo.deleteByName(name),
                request
        );
    }
}
