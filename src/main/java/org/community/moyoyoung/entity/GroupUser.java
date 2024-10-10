package org.community.moyoyoung.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_groupUser")
public class GroupUser {

    @ManyToOne
    private Group group;

    @ManyToOne
    private MyUser user;

}
