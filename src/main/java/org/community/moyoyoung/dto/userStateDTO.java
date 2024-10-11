package org.community.moyoyoung.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 김용

public interface userStateDTO {
    Long getGroupId();

    String getGroupTitle();

    Long getUserId();

    String getUserName();

    boolean getIsOwner();

    boolean getIsGroup();

    boolean getIsMeeting();
}
