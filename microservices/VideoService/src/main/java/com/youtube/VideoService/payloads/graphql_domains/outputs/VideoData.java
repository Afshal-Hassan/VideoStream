package com.youtube.VideoService.payloads.graphql_domains.outputs;


public record VideoData(
        String id,
        String title,
        String url,
        String uploadedAt,
        Long uploaderId) {
}
