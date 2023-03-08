package ru.nk.grooming.users.dto;

import lombok.Builder;
import lombok.Data;
import ru.nk.grooming.users.User;

@Data
@Builder
public class UserResponse {
    private final int statusCode;
    private final User data;
}
