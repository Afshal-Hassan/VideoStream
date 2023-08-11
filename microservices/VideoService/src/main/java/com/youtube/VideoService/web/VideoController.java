package com.youtube.VideoService.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller; 
import com.youtube.VideoService.services.VideoService;
import com.youtube.VideoService.payloads.inputs.VideoDataInput;
import com.youtube.VideoService.payloads.outputs.GenericResponse;






@Controller
public class VideoController {


    @Autowired
    private VideoService videoService;




    @MutationMapping("saveVideo")
    public GenericResponse saveVideo(@Argument VideoDataInput input) {
        videoService.saveVideo(input);
        return new GenericResponse("Video has been saved successfully", null);
    }

}
