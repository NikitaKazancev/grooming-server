package ru.nk.grooming.documents.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.components.salons.SalonEntity;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryFullData {
    private Long id;
    private Date month;
    private SalonEntity salon;
    private List<SalaryEmployeeFullData> employees;
}
