package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.community.moyoyoung.entity.MyUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDTO {

    private Long id;

    @NotNull(message = "Username is required.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,}_*$",
            message = "Username must be at least 4 characters long, contain only letters, numbers, and underscores.")
    private String username;

    @NotNull(message = "Nickname is required.")
    @Size(min = 2, max = 9, message = "Nickname must be between 2 and 9 characters long.")
    private String nickname;

    @NotNull(message = "Name is required.")
    private String name;

    @NotNull(message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10,11}$", message = "Phone number must be 10 or 11 digits long.")
    private String phoneNumber;

    public static MyUserDTO fromEntity(MyUser user) {
        return new MyUserDTO(
                user.getId(),
                user.getUsername(),
                user.getNickname(),
                user.getName(),
                user.getPhoneNumber()
        );
    }
}
