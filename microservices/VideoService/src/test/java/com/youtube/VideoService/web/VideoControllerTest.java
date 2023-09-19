package com.youtube.VideoService.web;

import com.youtube.VideoService.exceptions.BadRequestException;
import com.youtube.VideoService.payloads.global_domains.GenericResponse;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.services.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(VideoController.class)
public class VideoControllerTest {


    @MockBean
    private VideoService videoService;


    @Autowired
    private MockMvc mockMvc;

    private MockMultipartFile file;


    @BeforeEach
    public void setup() {
        file = new MockMultipartFile("file", "test.mp4", "video/mp4", "test video".getBytes());
    }


    /**
     * {@code Test Upload File } : to test upload file.
     * When video id is valid and file not provided then throws , {@link BadRequestException }
     * {@code 400 (Bad Request)}.
     */
    @Test
    public void testUploadFileWhenValidVideoIdAndFileNotProvidedThenReturnsBadRequest() throws Exception {
        // Arrange
        when(videoService.uploadFile(anyLong(), any())).thenReturn(String.valueOf(new BadRequestException("Requested file is not present")));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/video/update/1")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(file.getBytes()))
                .andExpect(status().isBadRequest());

    }
}