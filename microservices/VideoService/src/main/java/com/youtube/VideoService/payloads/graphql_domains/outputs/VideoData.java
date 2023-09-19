package com.youtube.VideoService.payloads.graphql_domains.outputs;


public record VideoData(
        Long id,
        String title,
        String url,
        String uploadedAt,
        Long uploaderId) {
}
