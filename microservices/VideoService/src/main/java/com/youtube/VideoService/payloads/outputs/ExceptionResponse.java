package com.youtube.VideoService.payloads.outputs;
 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString; 
import java.util.Date;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
}
