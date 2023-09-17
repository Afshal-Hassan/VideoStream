package com.youtube.VideoService.repos;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.youtube.VideoService.entities.Video;

import jakarta.transaction.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface VideoRepo extends JpaRepository<Video, Long> {

 

    @Modifying
    @Query(value = "update videos set file_name =:fileName where id =:videoId", nativeQuery = true)
    void updateFileNameOfVideoById(@Param("videoId") Long videoId,
                                    @Param("fileName") String fileName);

    Optional<Video> findById(Long id);

}
