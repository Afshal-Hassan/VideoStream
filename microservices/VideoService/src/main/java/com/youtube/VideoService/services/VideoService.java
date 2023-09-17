package com.youtube.VideoService.services;


import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import org.springframework.web.multipart.MultipartFile;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import reactor.core.publisher.Mono;

import java.io.IOException;


public interface VideoService {


    VideoData saveVideo(VideoDataInput input);

    String uploadFile(Long videoId, MultipartFile file) throws IOException;

    Mono streamVideo(String fileName);

}
