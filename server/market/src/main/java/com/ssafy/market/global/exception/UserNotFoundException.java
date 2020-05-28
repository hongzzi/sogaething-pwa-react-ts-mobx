package com.ssafy.market.global.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public UserNotFoundException(String message, Long Id){
        super(message + Id + "와/과 일치 하는 값이 없습 니다.");
        extensions.put("Domain",message);
        extensions.put("invalidId", Id);
    }

    public UserNotFoundException(String message){
        super(message + "일치 하는 회원이 없습 니다.");
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
