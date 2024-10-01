package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Group ownGroup;

    @OneToMany
    private Group group;
}
