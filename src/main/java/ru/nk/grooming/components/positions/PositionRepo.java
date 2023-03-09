package ru.nk.grooming.components.positions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends JpaRepository<PositionEntity, Long> {
}
