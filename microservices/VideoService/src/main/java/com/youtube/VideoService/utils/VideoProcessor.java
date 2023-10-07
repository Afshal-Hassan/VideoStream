package com.youtube.VideoService.utils;

import com.youtube.VideoService.clients.compressors.MediaProcessor;
import com.youtube.VideoService.exceptions.BadRequestException;
import com.youtube.VideoService.services.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;


@Component
public class VideoProcessor {


    @Value("${videos.path}")
    private String path;


    @Value("${directory.path}")
    private String directoryPath;


    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    private MediaProcessor mediaProcessor;


    /**
     * {@code Upload File } : upload video.
     *
     * @param file to upload video ,
     * @return the {@link String}, it is a fileName after uploading to the path,
     * @throws BadRequestException if file is empty or a file is not a video file,
     * @throws IOException         if file uploading process has some exception.
     */
    public String uploadVideo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BadRequestException("file can't be empty");
        }
        if (isNotVideoFile(file.getContentType())) {
            throw new BadRequestException("file should be of mp4 or video file");
        }


        File outputVideo = FileProcessor.createFile(directoryPath, "output_compressed.mp4");

        File originalVideo = FileProcessor.convertMultipartFileToFile(directoryPath, file);


        mediaProcessor.saveVideoQuality(originalVideo, outputVideo, 1280, 720);


        originalVideo.delete();

        return outputVideo.getName();
    }


    /**
     * {@code Load Video  } : load video file.
     *
     * @param fileName to load video ,
     * @return the {@link Mono<Resource>}, it is a video resource for streaming a video with Mono,
     */
    public Mono<Resource> loadVideo(String fileName) {
        return Mono.fromSupplier(() -> resourceLoader
                .getResource(String.format(
                        directoryPath + "/%s.mp4",
                        fileName)));
    }


    protected boolean isNotVideoFile(String contentType) {
        return contentType != null &&
                !contentType
                        .trim()
                        .toLowerCase()
                        .startsWith(AppConstants.VIDEO_TYPE);
    }
}
