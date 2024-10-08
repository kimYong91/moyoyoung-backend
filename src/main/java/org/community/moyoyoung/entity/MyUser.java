package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

import jakarta.validation.constraints.Size;

// 김용
// 유저
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class MyUser {

    public interface RegistrationGroup {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,}$",
            message = "Username must be at least 4 characters long, contain only letters, numbers, and underscores.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "Nickname is required.")
    @Size(min = 2, max = 9, message = "Nickname must be between 2 and 9 characters long.")
    @Column(unique = true)
    private String nickname;

    @NotNull(groups = RegistrationGroup.class, message = "Password is required.")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Name is required.")
    @Column(nullable = false)
    private String name;

    @NotNull(groups = RegistrationGroup.class, message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10,11}$", message = "Phone number must be 10 or 11 digits long.")
    @Column(nullable = false)
    private String phoneNumber;

    private boolean checkOnline;

    @Column(nullable = false)
    private Boolean disabled;

    @OneToOne
    private Group ownGroup;

    @OneToMany
    @Builder.Default
    private List<Group> group = new ArrayList<>();
}
