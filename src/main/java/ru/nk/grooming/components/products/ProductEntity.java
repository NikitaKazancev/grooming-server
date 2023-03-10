package ru.nk.grooming.components.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.types.EntityWithMerge;

@Data
@Entity
@Table(name = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements EntityWithMerge<ProductEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int duration;

    @Override
    public void merge(ProductEntity inputEntity) {
        int field = inputEntity.getDuration();
        if (field != 0) {
            this.setDuration(field);
        }
    }
}
