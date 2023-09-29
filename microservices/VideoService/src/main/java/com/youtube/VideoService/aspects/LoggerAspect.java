package com.youtube.VideoService.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAspect {


    public static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);


    @Before("execution(* com.youtube.VideoService.services.VideoServiceClient.saveVideo(..))")
    public void loggingBeforeAnyRequest(JoinPoint joinPoint) {
        Object input = joinPoint.getArgs()[0];
        LOG.debug("GraphQL request to save video details : {}", input);
    }


    @Before("execution(* com.youtube.VideoService.services.VideoServiceClient.uploadFile(..))")
    public void loggingBeforeAnyRequestUpdate(JoinPoint joinPoint) {
        Object videoId = joinPoint.getArgs()[0];
        Object file = joinPoint.getArgs()[1];
        LOG.debug("REST request for video id: " + videoId + "to update load video file : {}", file);
    }
}
