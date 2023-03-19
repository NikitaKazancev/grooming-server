package ru.nk.grooming.documents.registratons;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private Date date;
    private int duration;
    private int price;
    private Long salonId;
    private Long employeeId;
    private Long productId;
    private String comment;

    RegistrationEntity registrationEntity(Long userId) {
        return RegistrationEntity.builder()
                .date(this.date)
                .duration(this.duration)
                .price(this.price)
                .salonId(this.salonId)
                .employeeId(this.employeeId)
                .productId(this.productId)
                .userId(userId)
                .comment(this.comment)
                .build();
    }
}







