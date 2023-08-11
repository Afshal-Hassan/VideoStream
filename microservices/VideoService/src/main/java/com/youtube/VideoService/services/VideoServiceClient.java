package com.youtube.VideoService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.VideoService.mappers.VideoMapper;
import com.youtube.VideoService.payloads.inputs.VideoDataInput;
import com.youtube.VideoService.repos.VideoRepo;



@Service
public class VideoServiceClient implements VideoService {


    @Autowired
    private VideoRepo repo;




    @Override
    public void saveVideo(VideoDataInput input) {
        repo.save(VideoMapper.mapToEntity(input));
    }
    
}
