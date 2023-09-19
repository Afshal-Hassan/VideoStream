package com.youtube.VideoService.services;


import org.springframework.web.multipart.MultipartFile;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import reactor.core.publisher.Mono;

import java.io.IOException;


public interface VideoService {


    Mono saveVideo(Mono<VideoDataInput> input);

    String uploadFile(Long videoId, MultipartFile file) throws IOException;

    Mono streamVideo(String fileName);

}
