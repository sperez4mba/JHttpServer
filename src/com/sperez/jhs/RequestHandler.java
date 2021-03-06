package com.sperez.jhs;

import java.util.ArrayList;

public class RequestHandler implements HandlerInterface {
    private ReaderWriter reader;
    private String rawRequest = "";
    private RequestParser parser;
    private Request requestObject;

    @Override
    public void handle() {
        readRequest();
        createRequestObject();
    }

    void createRequestObject() {
        createParser();
        parser.parseRequest();
        requestObject = new Request(method(), resource(), protocol(), headers(), body());

    }

    public Request getRequestObject() {
        return requestObject;
    }

    @Override
    public void setupInputOutput(ReaderWriter reader) {
        this.reader = reader;
    }

    private void readRequest() {
        rawRequest = reader.readMessage();
        System.out.println("Request: " + rawRequest);
    }

    void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    public void setRequestObject(Request requestObject) {
        this.requestObject = requestObject;
    }

    void createParser() {
        parser = new RequestParser(rawRequest);
    }

    private String body() {
        return parser.getRequestBody();
    }

    private ArrayList<String> headers() {
        return parser.getRequestHeaders();
    }

    private String protocol() {
        return parser.getRequestProtocol();
    }

    private String resource() {
        return parser.getRequestedResource();
    }

    private String method() {
        return parser.getRequestMethod();
    }
}