package com.youtube.VideoService.payloads.graphql_domains.inputs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record VideoDataInput(
                @NotBlank(message = "video title can't be empty or null") String title,
                @NotBlank(message = "video filename can't be empty or null") String fileName,
                @NotNull(message = "uploader id can't be empty or null") Long uploaderId) {
}
