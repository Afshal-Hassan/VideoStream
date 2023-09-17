package com.youtube.VideoService.web;

import com.youtube.VideoService.payloads.global_domains.GenericResponse;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.services.VideoService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VideoControllerTest {

    @MockBean
    private VideoService videoService;

    @Autowired
    private VideoController videoController;

    @Test
    public void testSaveVideoWhenCalledWithValidInputThenReturnsExpectedResponse() {
        // Arrange
        VideoDataInput input = new VideoDataInput("Test Title", "Test Filename", 1L);
        VideoData videoData = new VideoData(1L, "Test Title", "Test URL", "Test UploadedAt", 1L);
        when(videoService.saveVideo(input)).thenReturn(videoData);

        // Act
        GenericResponse response = videoController.saveVideo(input);

        // Assert
        verify(videoService, times(1)).saveVideo(input);
        assertThat(response.getMessage()).isEqualTo("Video has been saved successfully");
        assertThat(response.getData()).isEqualTo(videoData);
    }

    @Test
    public void testSaveVideoWhenCalledWithInvalidInputThenThrowsException() {
        // Arrange
        VideoDataInput input = new VideoDataInput("", "", null);

        // Act & Assert
        assertThatThrownBy(() -> videoController.saveVideo(input))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void testUploadFileWhenValidVideoIdAndFileThenReturnGenericResponse() throws IOException {
        // Arrange
        Long videoId = 1L;
        MultipartFile file = new MockMultipartFile("file", "Hello, World!".getBytes());
        when(videoService.uploadFile(videoId, file)).thenReturn("File uploaded successfully");

        // Act
        GenericResponse response = videoController.uploadFile(videoId, file);

        // Assert
        verify(videoService, times(1)).uploadFile(videoId, file);
        assertThat(response.getMessage()).isEqualTo("Video has been saved successfully");
        assertThat(response.getData()).isEqualTo("File uploaded successfully");
    }

    @Test
    public void testUploadFileWhenInvalidVideoIdThenThrowException() throws IOException {
        // Arrange
        Long videoId = -1L;
        MultipartFile file = new MockMultipartFile("file", "Hello, World!".getBytes());
        when(videoService.uploadFile(videoId, file)).thenThrow(new IOException());

        // Act & Assert
        assertThatThrownBy(() -> videoController.uploadFile(videoId, file))
                .isInstanceOf(IOException.class);
    }
}