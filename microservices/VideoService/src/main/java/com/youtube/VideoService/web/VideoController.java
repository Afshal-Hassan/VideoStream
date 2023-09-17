package com.youtube.VideoService.web;


import java.io.IOException;


import com.youtube.VideoService.payloads.graphql_domains.outputs.VideoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.youtube.VideoService.services.VideoService;
import jakarta.validation.Valid;
import com.youtube.VideoService.payloads.global_domains.GenericResponse;
import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import reactor.core.publisher.Mono;


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
     * @return the generic response {@link GenericResponse}, with the Http status
     * {@code 200 (Ok)}.  ,
     */
    @MutationMapping("save")
    public GenericResponse saveVideo(@Valid @Argument VideoDataInput input) {
        VideoData result = videoService.saveVideo(input);
        return new GenericResponse
                (
                        "Video has been saved successfully",
                        result
                );
    }


    /**
     * {@code PUT for upload file } : to upload file and update video details.
     *
     * @param videoId to find video by it's id, with type {@link Long} ,
     * @param file    to upload file in a specified path, with type {@link MultipartFile}
     * @return the generic response {@link GenericResponse}, with the Http status
     * {@code 201 (Created)}.  ,
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update/{videoId}")
    public GenericResponse uploadFile(@PathVariable("videoId") Long videoId, @RequestParam("file") MultipartFile file) throws IOException {
        String result = videoService.uploadFile(videoId, file);
        return new GenericResponse
                (
                        "Video has been saved successfully",
                        result
                );
    }


    /**
     * {@code GET for streaming video } : to stream a video file or resource.
     *
     * @param fileName to find video by it's name, with type {@link String} ,
     * @return the mono with resource {@link Mono<Resource>}, with the Http status
     * {@code 200 (Ok)}.  ,
     */
    @GetMapping("/stream/{fileName}")
    public Mono<Resource> streamVideo(@PathVariable("fileName") String fileName) {
        return videoService.streamVideo(fileName);
    }

}
