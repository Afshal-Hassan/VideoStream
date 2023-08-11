package com.youtube.VideoService.payloads.outputs;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
import java.io.Serializable;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse implements Serializable {
 
    private String message; 
    private Object data;
}
