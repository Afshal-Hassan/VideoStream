package com.youtube.VideoService.payloads.graphql_domains.outputs;


import java.time.LocalDateTime;

public record VideoData(
        Long id,
        String title,
        String url,
        String uploadedAt,
        Long uploaderId) {
}
