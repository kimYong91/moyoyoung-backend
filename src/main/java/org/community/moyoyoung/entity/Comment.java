package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;


// 김용
// 댓글

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_comment")
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;

    private String nickName;
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDate;

    private boolean checkOnline;

    @ManyToOne
    private Post post;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private MyUser myUser;

}
