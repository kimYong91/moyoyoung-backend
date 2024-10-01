package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_meeting")
@Builder
// 정기 모임
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String meetingDate;
    private String content;
    private LocalDate dueDate;
    private String nickname;

    @OneToOne
    private Post post;

}
