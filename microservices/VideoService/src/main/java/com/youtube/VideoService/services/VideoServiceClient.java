package com.youtube.VideoService.services;


import java.io.IOException;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.exceptions.BadRequestException;
import com.youtube.VideoService.exceptions.NotFoundException;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.youtube.VideoService.mappers.VideoMapper;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.repos.VideoRepo;
import com.youtube.VideoService.utils.VideoProcessor;
import reactor.core.publisher.Mono;


@Service
public class VideoServiceClient implements VideoService {


    @Autowired
    private VideoRepo repo;


    @Autowired
    private VideoProcessor videoProcessor;


    @Override
    public VideoData saveVideo(VideoDataInput input) {
        Video video = repo.save(VideoMapper.mapToEntity(input));
        return VideoMapper.mapToData(video);
    }


    @Override
    public String uploadFile(Long videoId, MultipartFile file) throws IOException {
        repo
                .findById(videoId)
                .orElseThrow(() -> new NotFoundException("Video not found by id: " + videoId));

        String fileName = videoProcessor.uploadVideo(file);
        repo.updateFileNameOfVideoById(videoId, fileName);
        return fileName;
    }


    @Override
    public Mono<Resource> streamVideo(String fileName) {
        return videoProcessor.loadVideo(fileName);
    }
}
