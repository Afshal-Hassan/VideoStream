package com.youtube.VideoService.aspects;


import com.youtube.VideoService.payloads.graphql_domains.inputs.VideoDataInput;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;


@Aspect
@Component
public class LoggerAspect {


    public static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);


    @Before("execution(* com.youtube.VideoService.services.VideoServiceClient.saveVideo(..))")
    public void loggingBeforeAnyRequest(JoinPoint joinPoint) {
        Mono<VideoDataInput> input = (Mono) joinPoint.getArgs()[0];

        input.subscribe
                (
                        value -> LOG.debug("GraphQL request to save video details : {}", value)
                );
    }


    @Before("execution(* com.youtube.VideoService.services.VideoServiceClient.uploadFile(..))")
    public void loggingBeforeAnyRequestUpdate(JoinPoint joinPoint) {
        String videoId = (String) joinPoint.getArgs()[0];
        MultipartFile file = (MultipartFile) joinPoint.getArgs()[1];


        LOG.debug("REST request for video id : " + videoId + " to update load video file : {}",
                file.getOriginalFilename());
    }
}
