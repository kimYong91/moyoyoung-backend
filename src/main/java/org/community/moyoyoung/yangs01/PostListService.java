package org.community.moyoyoung.yangs01;

import org.community.moyoyoung.dto.*;

public interface PostListService {

    PostDTO getPostDetail(Long postId); // 게시글 상세 조회
    PageResponseDTO<PostListDTO> getPostList(PageRequestDTO pageRequestDTO); // 게시글 목록 조회
}
