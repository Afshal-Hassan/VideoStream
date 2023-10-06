package com.youtube.VideoService.web;


import com.youtube.VideoService.payloads.global_domains.GenericResponse;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import com.youtube.VideoService.services.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;


@RestController
@RequestMapping("/api/video")
@Validated
public class VideoController {


    @Autowired
    private VideoService videoService;


    /**
     * {@code GraphQL Mutation for save } : to save video details.
     *
     * @param input to store video details, with type {@link VideoDataInput} ,
     *              validatng the argument video input , with {@link Argument}  , used for graphql validation,
     * @return the mono,  {@link Mono} with the generic response {@link GenericResponse},
     * with the Http status {@code 200 (Ok)}.
     */
    @MutationMapping("save")
    public Mono saveVideo(@Valid @Argument VideoDataInput input) {
        return videoService.saveVideo(Mono.just(input))
                .map(result ->
                        new GenericResponse("Video details has been saved successfully", result)
                );
    }


    /**
     * {@code PUT for upload file } : to upload file and update video details.
     *
     * @param videoId to find video by it's id, with type {@link Long} ,
     * @param file    to upload file in a specified path, with type {@link MultipartFile}
     * @return the mono,  {@link Mono} with the generic response {@link GenericResponse},
     * with the Https status {@code 201 (Created)}.  ,
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update/{videoId}")
    public Mono uploadFile(@PathVariable("videoId") String videoId, @RequestParam("file") MultipartFile file) throws IOException {
        return videoService.uploadFile(videoId, file)
                .map(result ->
                        new GenericResponse("Video has been saved successfully", result)
                );
    }


    /**
     * {@code GET for streaming video } : to stream a video file or resource.
     *
     * @param fileName to find video by it's name, with type {@link String} ,
     * @return the mono with resource {@link Mono<Resource>}, with the Http status
     * {@code 200 (Ok)}.
     */
    @GetMapping("/stream/{fileName}")
    public Mono<Resource> streamVideo(@PathVariable("fileName") String fileName) {
        return videoService.streamVideo(fileName);
    }


    /**
     * {@code GET for all videos } : to get all videos.
     *
     * @param searchType to find video by searchType ( either all or by search ),
     *                   with type {@link String} ,
     * @param title      to find video by it's title if searchType is by search ,
     *                   with type {@link String} ,
     * @return the flux,  {@link Flux} with the generic response {@link GenericResponse},
     * with the Https status {@code 200 (Ok)}.  ,
     */
    @GetMapping("/get/{searchType}/{title}")
    public Flux getAllVideos(@PathVariable("searchType") String searchType, @PathVariable("title") String title) {
        return videoService.getAllVideos(searchType, title)
                .map(result ->
                        new GenericResponse("List of all videos by search type: " + searchType,
                                result
                        )
                );
    }

}
