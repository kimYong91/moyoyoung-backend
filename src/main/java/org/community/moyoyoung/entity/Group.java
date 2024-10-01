package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Table(name = "tbl_group")
// 소모임
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean checkOnline;
    private String country;
    private String category;
    private String title;
    private String content;
    private LocalDate dueDate;
    private boolean delFlag;  // 삭제 여부 (true일 경우 삭제된 것으로 간주)

    @OneToOne
    private Meeting meeting;

    @OneToOne
    private GroupImage groupImage;

    @OneToMany
    private List<MyUser> member = new ArrayList<>();

    @OneToOne
    private MyUser ownUser;

    @OneToMany
    private List<Post> postList = new ArrayList<>();
}
