package org.community.moyoyoung.dto;

import java.util.ArrayList;
import java.util.List;

import org.community.moyoyoung.entity.Group;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUserDTO {

    private Long id;
    private String username;
    private String nickName;
    
    @JsonIgnore
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private String password;
    private String name;
    private String phoneNumber;

    public void setHashedPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @Builder.Default
    private List<Group> ownGroup = new ArrayList<>();

    @Builder.Default
    private List<Group> group = new ArrayList<>();
}
