package com.youtube.VideoService.services;



import com.youtube.VideoService.payloads.inputs.VideoDataInput;



public interface VideoService {


    void saveVideo(VideoDataInput input);
    
}
