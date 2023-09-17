package com.youtube.VideoService.utils;

import com.youtube.VideoService.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoProcessorTest {

    @InjectMocks
    private VideoProcessor videoProcessor;

    @Mock
    private MultipartFile file;

    @BeforeEach
    public void setUp() {
        reset(file);
    }

    @Test
    public void testUploadVideoWhenFileIsEmptyThenThrowsBadRequestException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(true);

        // Act & Assert
        assertThatThrownBy(() -> videoProcessor.uploadVideo(file))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("file can't be empty");
    }

    @Test
    public void testUploadVideoWhenFileIsNotVideoThenThrowsBadRequestException() throws IOException {
        // Arrange
        when(file.isEmpty()).thenReturn(false);
        when(file.getContentType()).thenReturn("text/plain");

        // Act & Assert
        assertThatThrownBy(() -> videoProcessor.uploadVideo(file))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("file should be of mp4 or video file");
    }
}