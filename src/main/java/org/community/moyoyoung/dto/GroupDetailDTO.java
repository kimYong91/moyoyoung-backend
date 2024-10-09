package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 김용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDetailDTO {

    private GroupDTO group;
    private List<PostMiniDTO> postMiniList;
    private MeetingDTO meeting;
}
