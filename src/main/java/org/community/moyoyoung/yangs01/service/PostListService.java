package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.*;

public interface PostListService {

    PostListDTO get(Long postId); // 게시글 상세 조회
    PageResponseDTO<PostListDTO> list(PageRequestDTO pageRequestDTO); // 게시글 목록 조회
}
