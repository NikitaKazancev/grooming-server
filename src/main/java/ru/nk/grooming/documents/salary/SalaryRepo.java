package ru.nk.grooming.documents.salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepo extends JpaRepository<SalaryEntity, Long> {
    Optional<SalaryEntity> findByMonthAndSalonId(Date month, Long salonId);
    List<SalaryEntity> findAllByMonth(Date month);
    List<SalaryEntity> findAllBySalonId(Long id);
    List<SalaryEntity> findAllByMonthAndSalonId(Date month, Long id);
    @Query("select s, e from SalaryEntity s " +
            "join fetch s.employees se " +
            "join fetch EmployeeEntity e on se.employeeId = e.id " +
            "where s.id = :id")
    List<Object[]> findByIdWithEmployees(@Param("id") Long id);
}
