package com.youtube.VideoService.utils;

import com.youtube.VideoService.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.UUID;


@Component
public class VideoProcessor {


    @Value("${videos.path}")
    private String path;


    @Value("${directory.path}")
    private String directoryPath;


    @Autowired
    private ResourceLoader resourceLoader;


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

        String originalFilename = file.getOriginalFilename();

        String randomUUID = UUID.randomUUID().toString();
        String fileName = randomUUID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath = directoryPath + File.separator + fileName;


        File f = new File(directoryPath);


        if (!f.exists()) {
            f.mkdir();
        }


        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
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
