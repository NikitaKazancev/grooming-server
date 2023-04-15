package ru.nk.grooming.components.employees;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.types.EntityWithMerge;

@Entity
@Table(name = "employees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements EntityWithMerge<EmployeeEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    @Column(name = "position_id")
    private Long positionId;
    @Column(name = "salon_id")
    private Long salonId;

    @Override
    public void merge(EmployeeEntity inputEmployee) {
        String field = inputEmployee.getPhone();
        if (field != null) {
            this.setPhone(field);
        }

        field = inputEmployee.getAddress();
        if (field != null) {
            this.setAddress(field);
        }

        Long id = inputEmployee.getPositionId();
        if (id != null) {
            this.setPositionId(id);
        }

        id = inputEmployee.getSalonId();
        if (id != null) {
            this.setSalonId(id);
        }
    }
}
