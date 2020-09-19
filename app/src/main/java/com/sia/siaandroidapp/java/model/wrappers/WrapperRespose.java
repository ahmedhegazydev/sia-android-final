package com.sia.siaandroidapp.java.model.wrappers;


import com.sia.siaandroidapp.java.model.errors.ErrorsMessages;
import com.sia.siaandroidapp.java.model.responses.BasicResponse;

public class WrapperRespose<T> {
    private BasicResponse data;
    private ErrorsMessages errors;
    private Throwable t;

    public WrapperRespose(BasicResponse data, ErrorsMessages errors, Throwable t) {
        this.data = data;
        this.errors = errors;
        this.t = t;
    }

    public BasicResponse getData() {
        return data;
    }

    public ErrorsMessages getErrors() {
        return errors;
    }

    public Throwable getT() {
        return t;
    }
}
