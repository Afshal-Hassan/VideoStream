package com.youtube.VideoService.clients.compressors;

import java.io.File;
import java.io.IOException;


public interface FileCompressor {


    void saveVideoQuality(File originalVideo, File outputVideo, Integer width, Integer height) throws IOException;
}
