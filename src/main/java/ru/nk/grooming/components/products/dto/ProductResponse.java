package ru.nk.grooming.components.products.dto;

import lombok.Builder;
import lombok.Data;
import ru.nk.grooming.components.products.ProductEntity;

@Data
@Builder
public class ProductResponse {
    private final int statusCode;
    private final ProductEntity data;
}
