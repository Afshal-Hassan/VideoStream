package com.youtube.VideoService.payloads.outputs;

 



public record VideoData(
        String title,
        String url,
        Long uploaderId) {
}
