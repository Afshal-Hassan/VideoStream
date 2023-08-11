package com.youtube.VideoService.mappers;



import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.payloads.inputs.VideoDataInput; 
import com.youtube.VideoService.utils.UTCDateTimeUtils;





public final class VideoMapper {




    private VideoMapper() {

    }



    
    public static Video mapToEntity(VideoDataInput input) {
        Video video = new Video();
        video.setTitle(input.title());
        video.setUrl(input.url());
        video.setUploadedAt(UTCDateTimeUtils.utcTimeNow());
        video.setUploaderId(input.uploaderId());
        return video;
    } 
    
}
