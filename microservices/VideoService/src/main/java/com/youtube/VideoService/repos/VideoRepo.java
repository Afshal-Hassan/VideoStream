package com.youtube.VideoService.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.youtube.VideoService.entities.Video;



@Repository
public interface VideoRepo extends JpaRepository<Video,Long> {
    
}
