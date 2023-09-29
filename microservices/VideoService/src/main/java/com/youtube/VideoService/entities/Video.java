package com.youtube.VideoService.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Video implements Serializable {


    @Id
    @Column("id")
    private String id;


    @Column("title")
    private String title;


    @Column("file_name")
    private String fileName;


    @Column("uploaded_at")
    private LocalDateTime uploadedAt;


    @Column("uploader_id")
    private Long uploaderId;


}
