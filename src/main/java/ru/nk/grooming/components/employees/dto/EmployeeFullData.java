package ru.nk.grooming.components.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.components.positions.PositionEntity;
import ru.nk.grooming.components.salons.SalonEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFullData {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private PositionEntity position;
    private SalonEntity salon;
}
