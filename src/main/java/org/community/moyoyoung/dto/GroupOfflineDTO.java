package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 김용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupOfflineDTO {

    private Long id;
    private boolean checkOnline;
    private String country;
    private String category;
    private String title;
    private LocalDate createDate;

    private Long groupImageId;
}
