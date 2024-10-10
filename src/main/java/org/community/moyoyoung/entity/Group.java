package org.community.moyoyoung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// 김용
// 소모임
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Table(name = "tbl_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean checkOnline;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private LocalDate createDate;
    private boolean delFlag;  // 삭제 여부 (true일 경우 삭제된 것으로 간주)


    @JsonIgnore
    @OneToOne(mappedBy = "group")
    private Meeting meeting;

    @OneToOne
    private GroupImage groupImage;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MyUser> member = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    private MyUser ownUser;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", nullable = false)
    private List<Post> postList = new ArrayList<>();
}
