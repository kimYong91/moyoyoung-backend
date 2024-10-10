package org.community.moyoyoung.yangs01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.entity.PostImage;
import org.community.moyoyoung.repository.MyUserRepository;
import org.community.moyoyoung.repository.PostImageRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostCreateServiceImpl implements PostCreateService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final MyUserRepository myUserRepository;


    @Transactional
    @Override
    public Long register(PostCreateDTO postCreateDTO) {

        // myUserId로 사용자 조회
        MyUser myUser = myUserRepository.findById(postCreateDTO.getMyUserId())
                .orElseThrow(() -> {
                    // 로그 추가
                    log.error("User with ID {} not found", postCreateDTO.getMyUserId());
                    return new RuntimeException("User not found");
                });
         Post postCreate = new Post();
        postCreate.setMyUser(myUser);
        postCreate.setTitle(postCreateDTO.getTitle());
        postCreate.setContent(postCreateDTO.getContent());
        postCreate.setCreateDate(LocalDateTime.now());
        postCreate.setModifiedDate(LocalDateTime.now());


        List<MultipartFile> files = postCreateDTO.getFiles();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                PostImage postImage = savePostImage(file);
                postCreate.setPostImage(postImage);
            }
        }else {
            postCreate.setPostImage(null); // 이미지가 없으면 null로 설정
        }

        postRepository.save(postCreate);
        return postCreate.getId();
    }

    private PostImage savePostImage(MultipartFile file) {
        PostImage postImage = new PostImage();
        postImage.setFileName(file.getOriginalFilename());
        postImage.setFilePath("/path/to/file");
        return postImageRepository.save(postImage);
    }

    @Override
    public void modify(Long id, PostCreateDTO postCreateDTO) {
        Post postCreate = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postCreate.setTitle(postCreateDTO.getTitle());
        postCreate.setContent(postCreateDTO.getContent());

        List<MultipartFile> files = postCreateDTO.getFiles();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                PostImage postImage = savePostImage(file);
                postCreate.setPostImage(postImage);
            }
        }

        postRepository.save(postCreate);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }
}

