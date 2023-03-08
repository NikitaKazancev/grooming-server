package ru.nk.grooming.components.salons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepo extends JpaRepository<SalonEntity, Long> {
    Optional<SalonEntity> findByAddress(String address);
    Iterable<SalonEntity> findAllByPhone(String phone);
}
