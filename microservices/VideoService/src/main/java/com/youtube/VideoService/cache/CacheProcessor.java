package com.youtube.VideoService.cache;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.exceptions.InternalServerException;
import com.youtube.VideoService.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CacheProcessor {


    @Autowired
    private RedisTemplate redisTemplate;


    public void addToCache(String cacheKey, Video cacheValue) {
        String cacheName = AppConstants.CACHE_NAME;
        redisTemplate.opsForHash().put(cacheName, cacheKey, cacheValue);
    }


    public Video getFromCache(String cacheKey) {
        String cacheName = AppConstants.CACHE_NAME;


        if (Boolean.FALSE.equals(redisTemplate.hasKey(cacheName))) {
            throw new InternalServerException("Cache not found by name: " + cacheName);
        }


        Video video = (Video) redisTemplate.opsForHash().get(cacheName, cacheKey);
        return video;
    }


    public void updateFilenameFromCacheById(String cacheKey, String fileName) {
        String cacheName = AppConstants.CACHE_NAME;


        Video video = getFromCache(cacheKey);

        if (video != null) {
            video.setFileName(fileName);
            redisTemplate.opsForHash().put(cacheName, cacheKey, video);
        }
    }


    public List<Video> getAllVideosFromCache() {
        String cacheName = AppConstants.CACHE_NAME;


        if (Boolean.FALSE.equals(redisTemplate.hasKey(cacheName))) {
            throw new InternalServerException("Cache not found by name: " + cacheName);
        }


        List<Video> videos = redisTemplate.opsForHash().values(cacheName);
        return videos;
    }
}
