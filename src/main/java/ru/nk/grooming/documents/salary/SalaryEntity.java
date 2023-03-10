package ru.nk.grooming.documents.salary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.documents.salary.dto.SalaryEmployee;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "salary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date month;
    private Long salonId;
    @ElementCollection
    private List<SalaryEmployee> employees;
}
