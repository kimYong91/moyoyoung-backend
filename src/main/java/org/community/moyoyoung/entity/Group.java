package org.community.moyoyoung.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_group")
// 소모임
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean checkOnline;
    private String country;
    private String category;
    private String imageName;
    private String title;
    private String contents;
    private LocalDate dueDate;


    @OneToOne
    private Meeting meetingList;


    @OneToOne
    private GroupImage groupImage;


    @ManyToOne
    private List<MyUser> member = new ArrayList<>();

    @OneToOne
    private MyUser ownUser;
}
