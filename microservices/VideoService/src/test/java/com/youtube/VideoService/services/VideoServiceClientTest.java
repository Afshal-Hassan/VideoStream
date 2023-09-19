package com.youtube.VideoService.services;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.mappers.VideoMapper;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.repos.VideoRepo;
import com.youtube.VideoService.utils.VideoProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VideoServiceClientTest {


    @Mock
    private VideoRepo videoRepo;


    @Mock
    private VideoProcessor videoProcessor;


    @InjectMocks
    private VideoServiceClient videoServiceClient;


    private VideoDataInput input;

    private VideoData expectedVideoData;

    private MultipartFile file;


    @BeforeEach
    public void setUp() {
        input = new VideoDataInput("title", "fileName", 1L);
        expectedVideoData = new VideoData(1L, "title", "fileName", "2022-01-01T00:00:00", 1L);
        file = new MockMultipartFile("file", "fileName.mp4", "video/mp4", "test data".getBytes());
    }


    /**
     * {@code Test Save Video } : to save video file.
     * When input is valid and file then returns , {@link VideoData } and verify.
     */
    @Test
    public void testSaveVideoWhenValidInputThenReturnsSavedVideoData() {
        // Arrange
        when(videoRepo.save(any())).thenReturn(Mono.just(VideoMapper.mapToEntity(input)));

        // Act
        Mono<VideoData> result = videoServiceClient.saveVideo(Mono.just(input));

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(videoData -> videoData.title().equals(expectedVideoData.title()))
                .verifyComplete();
    }


    /**
     * {@code Test Upload File } : to upload video file.
     * When video id is valid and file provided then returns the file name and verify.
     */
    @Test
    public void testUploadFileWhenValidVideoIdAndFileProvidedThenReturnsFileName() throws IOException {
        // Arrange
        Long videoId = 1L;
        when(videoRepo.findById(videoId)).thenReturn(Mono.just(new Video()));
        when(videoProcessor.uploadVideo(file)).thenReturn("fileName.mp4");

        // Act
        String result = videoServiceClient.uploadFile(videoId, file);

        // Assert
        assertEquals("fileName.mp4", result);
    }
}