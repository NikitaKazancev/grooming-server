package ru.nk.grooming.users.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nk.grooming.documents.registratons.dto.RegistrationFullData;
import ru.nk.grooming.users.Role;
import ru.nk.grooming.users.User;

import java.util.Date;
import java.util.List;

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

    private List<RegistrationFullData> registrations;

    public static UserDTO create(User user) {
        return create(user, null);
    }
    public static UserDTO create(User user, List<RegistrationFullData> registrations) {
        try {
            return UserDTO.builder()
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .phone(user.getPhone())
                    .birthday(user.getBirthday())
                    .dogBreed(user.getDogBreed())
                    .registrations(registrations)
                    .build();
        } catch (Exception e) {
            return null;
        }

    }
}
