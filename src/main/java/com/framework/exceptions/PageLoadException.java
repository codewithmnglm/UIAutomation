package com.framework.exceptions;


public class PageLoadException extends Exception {


    public PageLoadException(String msg) {

        super(msg);

    }

    public PageLoadException(String message, Throwable cause) {
        super(message, cause);
    }

}
