package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.PageRequestDTO;
import org.community.moyoyoung.dto.PageResponseDTO;
import org.community.moyoyoung.dto.PostDTO;

public interface GroupPostListService {

    Long register(PostDTO postDTO);

    PostDTO get (Long id);

    void modify(PostDTO postDTO);

    void remove(Long id);

    PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO);
}
