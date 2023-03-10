package ru.nk.grooming.users.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private Date birthday;
    private String dogBreed;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static UserDTO create(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .dogBreed(user.getDogBreed())
                .build();
    }
}
