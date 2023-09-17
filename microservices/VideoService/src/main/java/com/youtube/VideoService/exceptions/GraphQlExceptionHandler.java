package com.youtube.VideoService.exceptions;


import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
 


 

public class GraphQlExceptionHandler implements DataFetcherExceptionHandler {
 




    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters parameters) {
         
        Throwable exception = parameters.getException(); 
        String errorMessage = "An error occurred while fetching data: " + exception.getMessage();
        

        DataFetchException graphQlError = new DataFetchException(errorMessage);
        

        return DataFetcherExceptionHandlerResult.newResult()
                .error(graphQlError)
                .build();
    }



    
    
}
