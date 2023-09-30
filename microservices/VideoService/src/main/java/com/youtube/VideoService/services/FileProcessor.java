package com.youtube.VideoService.services;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class FileProcessor {


    public static File createFile(String filename) {
        return new File(filename);
    }


    /**
     * {@code Convert Multipart file } : to convert multipart file to file.
     *
     * @param directoryPath to save file in specified directory ,
     * @param file          to convert multipart file to file , with type {@link MultipartFile} ,
     * @return the {@link File}, it is a file after converting multipart file to file,
     * @throws IOException if file uploading process has some exception.
     */
    public static File convertMultipartFileToFile(String directoryPath, MultipartFile file) throws IOException {
        File convertedFile = new File(directoryPath, file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }
}
