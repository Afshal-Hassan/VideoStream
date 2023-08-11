package com.youtube.VideoService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

 



@Entity
@Table(name = "videos") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Video {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "title",length = 200, nullable = false)
    private String title;


    @Column(name = "url", nullable = false)
    private String url;


    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;


    @Column(name = "uploader_id", nullable = false)
    private Long uploaderId;
    
    
}
