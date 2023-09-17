package com.youtube.VideoService.exceptions;
 


import graphql.ErrorType;
import graphql.GraphQLError; 
import graphql.language.SourceLocation; 
import java.util.*;
import java.lang.String;





 
public class DataFetchException extends RuntimeException implements GraphQLError {


    private String message;




    public DataFetchException(String message) {
        this.message = message;
    }




    @Override
    public String getMessage() {
        return message;
    }



    @Override
    public List<SourceLocation> getLocations() {
        return null; // Return source locations if available
    }



    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException; // Adjust the error type if needed
    }

     
     
    
}
