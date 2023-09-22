package com.youtube.VideoService.repos;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.youtube.VideoService.entities.Video;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;


@Repository
@Transactional
public interface VideoRepo extends ReactiveCrudRepository<Video, Long> {


    @Modifying
    @Query(value = "update videos set file_name =:fileName where id =:videoId")
    Mono<Void> updateFileNameOfVideoById(@Param("videoId") Long videoId,
                                   @Param("fileName") String fileName);

    Mono<Video> findById(Long id);

}
