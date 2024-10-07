package org.community.moyoyoung.yangs01.service;

import org.community.moyoyoung.dto.CommentDTO;
import org.community.moyoyoung.entity.MyUser;

import java.util.List;

public interface CommentService {

     // 댓글 생성 메서드: 새로운 댓글을 생성
     void createComment(CommentDTO commentDTO, MyUser currentUser);

     // 특정 게시글의 댓글 리스트 조회 메서드: 게시글 ID로 모든 댓글을 조회
     List<CommentDTO> getCommentsByPostId(Long postId);

     // 댓글 수정 메서드: 댓글 ID와 수정할 내용을 받아 댓글을 수정
     void updateComment(Long commentId, CommentDTO commentDTO);

     // 댓글 삭제 메서드: 댓글 ID를 받아 해당 댓글을 삭제
     void deleteComment(Long commentId);

}
