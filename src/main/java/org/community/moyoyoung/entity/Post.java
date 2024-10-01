package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_post")
// 게시판
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private LocalDate dueDate;


    @OneToOne
    private Meeting meetingList;


    @OneToOne
    private PostImage boardImage;


    @OneToMany(mappedBy = "comment_list", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;


    @ManyToOne
    private MyUser myUser;
}