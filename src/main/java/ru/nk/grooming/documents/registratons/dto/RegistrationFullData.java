package ru.nk.grooming.documents.registratons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.components.employees.EmployeeEntity;
import ru.nk.grooming.components.products.ProductEntity;
import ru.nk.grooming.components.salons.SalonEntity;
import ru.nk.grooming.users.dto.UserDTO;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFullData {
    private Long id;
    private Date date;
    private int duration;
    private int price;
    private SalonEntity salon;
    private EmployeeEntity employee;
    private ProductEntity product;
    private UserDTO user;
    private String comment;
}
