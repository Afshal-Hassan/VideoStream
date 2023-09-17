package com.youtube.VideoService.services;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.exceptions.NotFoundException;
import com.youtube.VideoService.mappers.VideoMapper;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.repos.VideoRepo;
import com.youtube.VideoService.utils.UTCDateTimeUtils;
import com.youtube.VideoService.utils.VideoProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VideoServiceClientTest {

    @Mock
    private VideoRepo repo;

    @Mock
    private VideoProcessor videoProcessor;

    @InjectMocks
    private VideoServiceClient videoServiceClient;

    @Mock
    private MultipartFile file;

    private VideoDataInput input;
    private Video video;

    @BeforeEach
    public void setUp() {
        input = new VideoDataInput("title", "fileName", 1L);
        video = new Video();
        video.setId(1L);
        video.setTitle(input.title());
        video.setFileName("Test");
        video.setUploadedAt(UTCDateTimeUtils.utcTimeNow());
        video.setUploaderId(input.uploaderId());
    }

    @Test
    public void testSaveVideoReturnsCorrectVideoData() {
        when(repo.save(any(Video.class))).thenReturn(video);
        VideoData result = videoServiceClient.saveVideo(input);
        assertThat(result).isEqualTo(VideoMapper.mapToData(video));
    }

    @Test
    public void testSaveVideoThrowsBadRequestExceptionWhenInputIsNull() {
        VideoDataInput nullInput = null;
        assertThatThrownBy(() -> videoServiceClient.saveVideo(nullInput))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testUploadFileWhenValidVideoIdAndFileThenReturnsFileName() throws IOException {
        when(repo.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoProcessor.uploadVideo(any(MultipartFile.class))).thenReturn("fileName");
        String result = videoServiceClient.uploadFile(1L, file);
        assertThat(result).isEqualTo("fileName");
    }

    @Test
    public void testUploadFileWhenInvalidVideoIdThenThrowsNotFoundException() throws IOException {
        when(repo.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> videoServiceClient.uploadFile(1L, file))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    public void testUploadFileWhenFileUploadFailsThenThrowsIOException() throws IOException {
        when(repo.findById(anyLong())).thenReturn(Optional.of(video));
        when(videoProcessor.uploadVideo(any(MultipartFile.class))).thenThrow(IOException.class);
        assertThatThrownBy(() -> videoServiceClient.uploadFile(1L, file))
                .isInstanceOf(IOException.class);
    }
}