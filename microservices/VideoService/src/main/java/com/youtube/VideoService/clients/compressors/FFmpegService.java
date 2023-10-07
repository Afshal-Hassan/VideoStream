package com.youtube.VideoService.clients.compressors;


import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class FFmpegService implements MediaProcessor {


    @Value("${ffmpeg.path}")
    public String ffmpegPath;


    @Override
    public void saveVideoQuality(File originalVideo, File outputVideo, Integer width, Integer height, int bitRate) throws IOException {
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(originalVideo.getAbsolutePath())
                .addOutput(outputVideo.getAbsolutePath())
                .setVideoCodec("libx264")
                .setVideoResolution(width, height)
                .setVideoBitRate(bitRate)
                .done();


        FFmpegExecutor executor = new FFmpegExecutor(new FFmpeg(ffmpegPath + "/ffmpeg"));
        executor.createJob(builder).run();
    }
}