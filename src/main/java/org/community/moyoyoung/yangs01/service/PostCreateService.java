package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.entity.MyUser;
import org.springframework.transaction.annotation.Transactional;

public interface PostCreateService {


        Long register(PostCreateDTO postCreateDTO, MyUser myUser);
        // 게시글 등록

        void modify(Long id, PostCreateDTO postCreateDTO);
        // 기존 게시글 수정 (게시글 ID와 수정할 데이터 포함)

        void remove (Long id);

}


