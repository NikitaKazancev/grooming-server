package ru.nk.grooming.components.positions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PositionRepo extends JpaRepository<PositionEntity, Long> {
    Optional<PositionEntity> findByName(String name);
    @Transactional
    void deleteByName(String name);
}
