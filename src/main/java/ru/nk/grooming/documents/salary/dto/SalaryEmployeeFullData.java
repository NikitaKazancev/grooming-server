package ru.nk.grooming.documents.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.components.employees.EmployeeEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEmployeeFullData {
    private EmployeeEntity employee;
    private int salary;
}
