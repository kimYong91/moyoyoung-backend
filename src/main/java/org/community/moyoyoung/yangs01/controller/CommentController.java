package org.community.moyoyoung.yangs01.controller;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.CommentDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.yangs01.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/allBoard")
public class CommentController {

    private final CommentService commentService;


    // 댓글 생성
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long postId,
                                              @RequestBody CommentDTO commentDTO,
                                              @SessionAttribute("currentUser") MyUser currentUser) {
        commentService.createComment(postId, commentDTO, currentUser); // 댓글 생성 서비스 호출
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created 응답 반환
    }

    // 특정 포스트에 대한 댓글 목록 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.getCommentsByPostId(postId); // 댓글 조회 서비스 호출
        return ResponseEntity.ok(comments); // 200 OK 응답과 함께 댓글 목록 반환
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @RequestBody CommentDTO commentDTO) {
        commentService.updateComment(commentId, commentDTO); // 댓글 수정 서비스 호출
        return ResponseEntity.noContent().build(); // 204 No Content 응답 반환
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId); // 댓글 삭제 서비스 호출
        return ResponseEntity.noContent().build(); // 204 No Content 응답 반환
    }
}
