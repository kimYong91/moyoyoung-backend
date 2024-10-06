package org.community.moyoyoung.kimyong91.controller;

import org.community.moyoyoung.kimyong91.service.GroupImageService;
import org.community.moyoyoung.kimyong91.CustomFileUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(GroupImageController.class)
class GroupImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupImageService groupImageService;

    @MockBean
    private CustomFileUtil customFileUtil;

    @Test
    void testUploadImage() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "Test Image Content".getBytes());

        // 파일 저장 Mock
        Mockito.when(customFileUtil.saveFile(any())).thenReturn(List.of("saved_test.jpg"));

        // 서비스 저장 Mock
        Mockito.when(groupImageService.saveImages(any(), any())).thenReturn(List.of());

        mockMvc.perform(multipart("/image")
                        .file(mockFile))
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveImage() throws Exception {
        // 삭제 Mock 설정
        Mockito.doNothing().when(groupImageService).removeImage(anyLong());

        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("SUCCESS")));
    }
}
