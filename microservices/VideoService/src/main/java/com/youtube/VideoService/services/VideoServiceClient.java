package com.youtube.VideoService.services;


import java.io.IOException;
import java.util.Map;

import com.youtube.VideoService.cache.CacheProcessor;
import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.enums.Search;
import com.youtube.VideoService.exceptions.BadRequestException;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class VideoServiceClient implements VideoService {


    @Autowired
    private VideoRepo repo;


    @Autowired
    private VideoProcessor videoProcessor;


    @Autowired
    private CacheProcessor cacheProcessor;


    @Override
    public Mono<VideoData> saveVideo(Mono<VideoDataInput> input) {
        return input
                .map(VideoMapper::mapToEntity)
                .doOnNext(video -> cacheProcessor.addToCache(video.getId(), video))
                .flatMap(video -> Mono.just(VideoMapper.mapToData(video)));
    }


    @Override
    public Mono<String> uploadFile(String videoId, MultipartFile file) throws IOException {
        Video video = cacheProcessor.getFromCache(videoId);
        if (video == null) {
            Mono.error(new NotFoundException("Video not found by id: " + videoId));
        }

        String fileName = videoProcessor.uploadVideo(file);
        cacheProcessor.updateFilenameFromCacheById(videoId, fileName);
        return Mono.just(fileName);
    }


    @Override
    public Mono<Resource> streamVideo(String fileName) {
        return videoProcessor.loadVideo(fileName);
    }


    @Override
    public Flux<VideoData> getAllVideos(String searchType, String title) {
        if (Search.isBySearch(searchType)) {

            return repo.findVideosByTitle(title)
                    .map(video -> VideoMapper.mapToData(video));

        } else if (Search.isAll(searchType)) {

            Map<String, Video> allVideosFromCache = cacheProcessor.getAllVideosFromCache();
            return Flux.fromIterable(allVideosFromCache.values())
                    .map(video -> VideoMapper.mapToData(video));

        } else {
            throw new BadRequestException("Invalid search type");
        }
    }
}
