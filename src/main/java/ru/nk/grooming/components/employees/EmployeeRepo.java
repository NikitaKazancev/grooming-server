package ru.nk.grooming.components.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query("" +
            "SELECT e, s, p " +
            "from EmployeeEntity e " +
            "JOIN SalonEntity s on e.salonId = s.id " +
            "JOIN PositionEntity p on e.positionId = p.id " +
            "where e.id = :id" +
            "")
    List<Object[]> findByIdWithJoin(@Param("id") Long id);
    List<EmployeeEntity> findAllBySalonId(Long salonId);
    List<EmployeeEntity> findAllByPositionId(Long positionId);
    Optional<EmployeeEntity> findByName(String name);
    @Transactional
    void deleteAllBySalonId(Long salonId);
    @Transactional
    void deleteAllByPositionId(Long positionId);
}
