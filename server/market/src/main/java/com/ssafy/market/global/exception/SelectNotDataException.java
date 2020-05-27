package com.ssafy.market.global.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectNotDataException extends RuntimeException implements GraphQLError {

//    private Long productId;
    private Map<String, Object> extensions = new HashMap<>();

    public SelectNotDataException(String message){
        super(message +"조회되는 데이터가 없습니다.");
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
