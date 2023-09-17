package com.youtube.VideoService.aspects;


import com.youtube.VideoService.annotations.ResponseHeader;
import com.youtube.VideoService.exceptions.InvalidAnnotationException;
import com.youtube.VideoService.utils.HeaderUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class ResponseHeaderAspect {


    /**
     * {@code Response Header } : Set response headers.
     *
     * @param responseHeader to set headers, pointcut after returning {@link ResponseHeader},
     * @return the {@link null},
     * @throws InvalidAnnotationException if responseHeader is empty.
     */
    @AfterReturning(pointcut = "@annotation(responseHeader)")
    public void setHeader(ResponseHeader responseHeader) {
        if (HeaderUtils.getHeaders().isEmpty()) {
            throw new InvalidAnnotationException("Response Header annotation can't be used with null header values");
        }

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HeaderUtils.getHeaders().forEach((key, value) -> {
            response.addHeader(key, value.toString());
        });
    }
}
