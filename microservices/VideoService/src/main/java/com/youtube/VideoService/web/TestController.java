package com.youtube.VideoService.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private CacheManager cacheManager;


    @GetMapping("/cache/{name}")
    public Cache getCache(@PathVariable("name") String name) {
        return cacheManager.getCache(name);
    }


    @GetMapping("/test")
    public void getTest() {
        System.out.println("tested");
    }
}
