package org.community.moyoyoung.yangs01;

import org.community.moyoyoung.dto.CommentDTO;
import org.community.moyoyoung.dto.PostDetailDTO;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.repository.PostDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostDetailServiceImpl implements PostDetailService {

    @Autowired
    private PostDetailRepository postDetailRepository;

    @Override
    public PostDetailDTO getPostDetail(Long id) {
        Optional<Post> postDetailOptional = postDetailRepository.findById(id);
        if (postDetailOptional.isPresent()) {
            Post post = postDetailOptional.get();
            return convertToDTO(post);  // 엔티티를 DTO로 변환
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }

    private PostDetailDTO convertToDTO(Post postDetail) {
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        postDetailDTO.setId(postDetail.getId());
        postDetailDTO.setTitle(postDetail.getTitle());
        postDetailDTO.setContent(postDetail.getContent());
        postDetailDTO.setPostImage(postDetail.getPostImage());
        postDetailDTO.setDueDate(postDetail.getDueDate());

        // 닉네임과 실명 각각 설정
        postDetailDTO.setNickName(postDetail.getMyUser().getNickName());
        postDetailDTO.setName(postDetail.getMyUser().getName());

        postDetailDTO.setCommentList(postDetail.getCommentList().stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getContent(), comment.getDueDate()))
                .toList());

        return postDetailDTO;
    }
}
