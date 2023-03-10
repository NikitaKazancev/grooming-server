package ru.nk.grooming.components.salons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.types.EntityWithMerge;

@Data
@Entity
@Table(name = "salons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalonEntity implements EntityWithMerge<SalonEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;

    @Override
    public void merge(SalonEntity inputEntity) {
        String field = inputEntity.getName();
        if (field != null) {
            this.setName(field);
        }

        field = inputEntity.getPhone();
        if (field != null) {
            this.setPhone(field);
        }
    }
}
