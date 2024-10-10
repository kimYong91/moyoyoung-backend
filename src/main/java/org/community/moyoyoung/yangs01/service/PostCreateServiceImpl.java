package org.community.moyoyoung.yangs01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.community.moyoyoung.dto.PostCreateDTO;
import org.community.moyoyoung.entity.Group;
import org.community.moyoyoung.entity.MyUser;
import org.community.moyoyoung.entity.Post;
import org.community.moyoyoung.entity.PostImage;
import org.community.moyoyoung.repository.GroupRepository;
import org.community.moyoyoung.repository.MyUserRepository;
import org.community.moyoyoung.repository.PostImageRepository;
import org.community.moyoyoung.repository.PostRepository;
import org.modelmapper.ModelMapper;
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
    private final GroupRepository groupRepository;
    private final ModelMapper modelMapper;


    @Transactional
    @Override
    public Long register(PostCreateDTO postCreateDTO, MyUser myUser) {
            Post post = modelMapper.map(postCreateDTO, Post.class);
            post.setCreateDate(LocalDateTime.now());

            List<String> uploadFileName = postCreateDTO.getUploadFileName();
        if (uploadFileName != null && !uploadFileName.isEmpty()) {
            for (int i = 0; i < uploadFileName.size(); i++) {
                String fileName = uploadFileName.get(i);
                String upLoadFileName =postCreateDTO.getFile().get(i).getOriginalFilename();
                PostImage postImage = new PostImage();
                postImage.setFileName(fileName);
                postImage.setCreateDate(LocalDateTime.now());
                postImage.setUpLoadFileName(upLoadFileName);
                postImage.setMimeType(postCreateDTO.getFile().get(i).getContentType());
                post.setPostImage(postImage);
                post.setMyUser(postCreateDTO.getMyUser());

                postImageRepository.save(postImage);
            }
        }

        Post reault = postRepository.save(post);
        return reault.getId();


//        Post postCreate = new Post();
//        postCreate.setMyUser(myUser); // myUser를 직접 전달받음
//        postCreate.setTitle(postCreateDTO.getTitle());
//        postCreate.setContent(postCreateDTO.getContent());
//        postCreate.setCreateDate(LocalDateTime.now());
//        postCreate.setModifiedDate(LocalDateTime.now());
//
//        // 이미지 저장 로직 (주석 처리된 부분) 필요시 추가
//        List<MultipartFile> files = postCreateDTO.getFiles();
//        if (files != null && !files.isEmpty()) {
//            // 첫 번째 이미지 파일만 저장 (필요시 반복문으로 변경 가능)
//            MultipartFile file = files.get(0);
//            PostImage postImage = savePostImage(file);
//            postCreate.setPostImage(postImage);
//        } else {
//            postCreate.setPostImage(null); // 이미지가 없으면 null로 설정
//        }
//
////        // groupId가 null이 아닐 경우 그룹 조회 및 설정
////        if (postCreateDTO.getGroupId() != null) {
////            Group group = groupRepository.findById(postCreateDTO.getGroupId())
////                    .orElseThrow(() -> new RuntimeException("Group not found"));
////        } else {
////            throw new RuntimeException("Group ID must not be null");
////        }
//
//        postRepository.save(postCreate);
//        return postCreate.getId();
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

