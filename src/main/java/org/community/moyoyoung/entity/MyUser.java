package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_member")
// 유저
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String nickName;
    private String password;
    private String name;
    private String phoneNumber;

    @OneToMany
    private List<Group> ownGroup = new ArrayList<>();

    @OneToMany
    private List<Group> group = new ArrayList<>();
}
