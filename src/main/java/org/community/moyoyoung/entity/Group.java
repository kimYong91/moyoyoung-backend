package org.community.moyoyoung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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


    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL)
    private Meeting meeting;


    @OneToOne(mappedBy = "group", cascade = CascadeType.ALL)
    private GroupImage groupImage; // 컬럼에서 보이지 않음


    @ManyToOne
    private MyUser ownUser;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id", nullable = false)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    List<GroupUser> groupUsers = new ArrayList<>();

}
