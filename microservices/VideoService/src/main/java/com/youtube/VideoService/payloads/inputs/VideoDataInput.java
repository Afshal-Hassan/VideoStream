package com.youtube.VideoService.payloads.inputs;





public record VideoDataInput(String title,
        String url,
        Long uploaderId) {
}
