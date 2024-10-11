package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.*;

import java.util.List;


public interface PostService {

     PostDTO get(Long id); // 게시글 상세 조회
     PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO);
     List<PostDTO> getAllPosts(Long group_id);
}

