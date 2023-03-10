package ru.nk.grooming.components.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
    @Query("" +
            "SELECT e, s, p " +
            "from EmployeeEntity e " +
            "JOIN SalonEntity s on e.salonId = s.id " +
            "JOIN PositionEntity p on e.positionId = p.id " +
            "where e.name = :name" +
    "")
    List<Object[]> findByNameWithJoin(@Param("name") String name);
    Iterable<EmployeeEntity> findAllBySalonId(Long salonId);
    Iterable<EmployeeEntity> findAllByPositionId(Long positionId);
    Optional<EmployeeEntity> findByName(String name);
    Void deleteAllBySalonId(Long salonId);
    Void deleteAllByPositionId(Long positionId);
}
