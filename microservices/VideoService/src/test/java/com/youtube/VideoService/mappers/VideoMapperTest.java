package com.youtube.VideoService.mappers;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class VideoMapperTest {

    @Test
    public void testMapToEntityWhenInputIsNotNullThenReturnsCorrectVideo() {
        // Arrange
        String title = "Test Title";
        Long uploaderId = 1L;
        VideoDataInput input = new VideoDataInput(title, "Test Filename", uploaderId);

        // Act
        Video video = VideoMapper.mapToEntity(input);

        // Assert
        assertThat(video).isNotNull();
        assertThat(video.getTitle()).isEqualTo(title);
        assertThat(video.getUploadedAt()).isNotNull();
        assertThat(video.getUploaderId()).isEqualTo(uploaderId);
    }

    @Test
    public void testMapToDataWhenVideoProvidedThenReturnsCorrectVideoData() {
        // Arrange
        Long id = 1L;
        String title = "Test Title";
        String fileName = "Test Filename";
        LocalDateTime uploadedAt = LocalDateTime.now();
        Long uploaderId = 1L;
        Video video = new Video();
        video.setId(id);
        video.setTitle(title);
        video.setFileName(fileName);
        video.setUploadedAt(uploadedAt);
        video.setUploaderId(uploaderId);

        // Act
        VideoData videoData = VideoMapper.mapToData(video);

        // Assert
        assertThat(videoData).isNotNull();
        assertThat(videoData.id()).isEqualTo(id);
        assertThat(videoData.title()).isEqualTo(title);
        assertThat(videoData.url()).isEqualTo(fileName);
        assertThat(videoData.uploadedAt()).isEqualTo(uploadedAt.toString());
        assertThat(videoData.uploaderId()).isEqualTo(uploaderId);
    }
}