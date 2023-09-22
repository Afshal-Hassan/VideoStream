package com.youtube.VideoService.mappers;


import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.utils.UTCDateTimeUtils;

import java.util.UUID;


public final class VideoMapper {


    private VideoMapper() {

    }


    public static Video mapToEntity(VideoDataInput input) {
        Video video = new Video();
        video.setId(UUID.randomUUID().toString());
        video.setTitle(input.title());
        video.setUploadedAt(UTCDateTimeUtils.utcTimeNow());
        video.setUploaderId(input.uploaderId());
        return video;
    }

    public static VideoData mapToData(Video video) {
        return new VideoData(
                video.getId(),
                video.getTitle(),
                video.getFileName(),
                video.getUploadedAt().toString(),
                video.getUploaderId()
        );
    }

}
