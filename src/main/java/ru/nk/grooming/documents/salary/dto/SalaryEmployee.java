package ru.nk.grooming.documents.salary.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEmployee {
    private Long employeeId;
    private int salary;
}
