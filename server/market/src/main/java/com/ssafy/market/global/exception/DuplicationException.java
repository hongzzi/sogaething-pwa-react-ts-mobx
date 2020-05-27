package com.ssafy.market.global.exception;

public class DuplicationException extends Exception{
    public DuplicationException(String msg,Throwable t) {
        super(msg,t);
    }

    public DuplicationException(String msg) {
        super(msg);
    }
}
