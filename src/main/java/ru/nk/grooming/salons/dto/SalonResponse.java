package ru.nk.grooming.salons.dto;

import lombok.Builder;
import lombok.Data;
import ru.nk.grooming.salons.SalonEntity;

@Data
@Builder
public class SalonResponse {
    private final int statusCode;
    private final SalonEntity data;
}
