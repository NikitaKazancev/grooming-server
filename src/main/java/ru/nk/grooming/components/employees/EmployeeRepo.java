package ru.nk.grooming.components.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
    @Query("" +
            "SELECT e, s, p " +
            "from EmployeeEntity e " +
            "JOIN SalonEntity s on e.salonId = s.id " +
            "JOIN PositionEntity p on e.positionId = p.id " +
            "where e.name = :name" +
    "")
    List<Object[]> findByName(@Param("name") String name);
}
