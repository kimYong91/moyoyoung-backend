package org.community.moyoyoung.yangs01;

import lombok.RequiredArgsConstructor;
import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.entity.PostImage;
import org.community.moyoyoung.repository.PostImageRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCreateServiceImpl implements PostCreateService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;


    @Override
    public Long register(PostCreateDTO postCreateDTO) {
        Post postCreate = new Post();
        postCreate.setTitle(postCreateDTO.getTitle());
        postCreate.setContent(postCreateDTO.getContent());
        postCreate.setCreateDate(postCreateDTO.getCreateDate());

        List<MultipartFile> files = postCreateDTO.getFiles();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                PostImage postImage = savePostImage(file);
                postCreate.setPostImage(postImage);
            }
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
    }

