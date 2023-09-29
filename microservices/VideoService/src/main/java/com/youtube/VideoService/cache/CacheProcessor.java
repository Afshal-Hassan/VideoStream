package com.youtube.VideoService.cache;

import com.youtube.VideoService.entities.Video;
import com.youtube.VideoService.exceptions.InternalServerException;
import com.youtube.VideoService.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CacheProcessor {


    @Autowired
    private CacheManager cacheManager;


    public void addToCache(String cacheKey, Object cacheValue) {
        Cache cache = cacheManager.getCache(AppConstants.CACHE_NAME);

        if (cache == null) {
            throw new InternalServerException("Cache not found by name: " + AppConstants.CACHE_NAME);
        }

        cache.put(cacheKey, cacheValue);
    }


    public Video getFromCache(String cacheKey) {
        Cache cache = cacheManager.getCache(AppConstants.CACHE_NAME);

        if (cache == null) {
            throw new InternalServerException("Cache not found by name: " + AppConstants.CACHE_NAME);
        }


        Cache.ValueWrapper valueWrapper = cache.get(cacheKey);

        if (valueWrapper != null) {
            Object cachedValue = valueWrapper.get();

            if (cachedValue instanceof Video) {
                return (Video) cachedValue;
            }
        }
        return null;
    }


    public void updateFilenameFromCacheById(String cacheKey, String fileName) {
        Cache cache = cacheManager.getCache(AppConstants.CACHE_NAME);

        if (cache == null) {
            throw new InternalServerException("Cache not found by name: " + AppConstants.CACHE_NAME);
        }

        Video video = getFromCache(cacheKey);

        if (video != null) {
            video.setFileName(fileName);
            cache.put(cacheKey, video);
        }
    }


    public Map<String, Video> getAllVideosFromCache() {
        Cache cache = cacheManager.getCache(AppConstants.CACHE_NAME);


        if (cache == null) {
            throw new InternalServerException("Cache not found by name: " + AppConstants.CACHE_NAME);
        }


        Map<String, Video> allItems = new HashMap<>();


        for (String cacheKey : cacheManager.getCacheNames()) {
            Cache.ValueWrapper valueWrapper = cache.get(cacheKey);

            if (valueWrapper != null) {
                Object cachedValue = valueWrapper.get();

                if (cachedValue instanceof Video) {
                    allItems.put(cacheKey, (Video) cachedValue);
                }
            }
        }

        return allItems;
    }
}
