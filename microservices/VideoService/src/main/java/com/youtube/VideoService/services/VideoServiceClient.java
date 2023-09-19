package com.youtube.VideoService.services;


import java.io.IOException;

import com.youtube.VideoService.exceptions.NotFoundException;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
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
    public Mono<VideoData> saveVideo(Mono<VideoDataInput> input) {
        return input
                .map(VideoMapper::mapToEntity)
                .flatMap(entity -> repo.save(entity))
                .flatMap(video -> Mono.just(VideoMapper.mapToData(video)));
    }


    @Override
    public String uploadFile(Long videoId, MultipartFile file) throws IOException {
        repo
                .findById(videoId)
                .switchIfEmpty(
                        Mono.error(new NotFoundException("Video not found by id: " + videoId))
                );

        String fileName = videoProcessor.uploadVideo(file);
        repo.updateFileNameOfVideoById(videoId, fileName);
        return fileName;
    }


    @Override
    public Mono<Resource> streamVideo(String fileName) {
        return videoProcessor.loadVideo(fileName);
    }
}
