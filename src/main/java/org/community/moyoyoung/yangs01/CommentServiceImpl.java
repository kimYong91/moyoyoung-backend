package org.community.moyoyoung.yangs01;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.CommentDTO;
import org.community.moyoyoung.entity.Comment;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.repository.CommentRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void createComment(CommentDTO commentDTO, MyUser currentUser) {
        Comment comment = new Comment(); // 새로운 댓글 객체 생성

        comment.setContent(commentDTO.getContent()); // 댓글 내용 설정
        comment.setMyUser(currentUser); // 작성자 이름 설정
        comment.setCreateDate(LocalDateTime.now()); // 생성일 설정

        // 댓글 저장
        commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        // 특정 포스트에 대한 댓글 목록 조회
        List<Comment> comments = commentRepository.findByPostId(postId);

        // 댓글 엔티티를 DTO로 변환하여 반환
        return comments.stream()
                .map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getContent(),
                        comment.getMyUser(),
                        comment.getCreateDate()
                ))
                .toList();
    }

    @Override
    public void updateComment(Long commentId, CommentDTO commentDTO) {
        // 댓글 ID로 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found")); // 댓글이 없으면 예외 발생

        // 댓글 내용을 업데이트
        comment.setContent(commentDTO.getContent());

        // 댓글 저장
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        // 댓글 ID로 댓글 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found")); // 댓글이 없으면 예외 발생

        // 댓글 삭제
        commentRepository.delete(comment);
    }

}