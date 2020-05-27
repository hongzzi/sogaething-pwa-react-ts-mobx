package com.ssafy.market.global.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateProductException extends RuntimeException implements GraphQLError {

//    private Long productId;
    private Map<String, Object> extensions = new HashMap<>();

    public DuplicateProductException(String message, Long Id){
        super(message + Id + "에 중복되는 상품이 존재합니다.");
        extensions.put("Domain",message);
        extensions.put("invalidId", Id);
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
