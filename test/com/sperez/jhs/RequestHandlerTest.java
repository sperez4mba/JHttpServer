package com.sperez.jhs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {
    private RequestHandler handler;

    @Before
    public void setUp() throws Exception {
        handler = new RequestHandler();
    }

    @Test
    public void testParseEmptyRequest() {
        String rawRequest = "";
        handler.setRawRequest(rawRequest);
        handler.parseRequest();

        assertEquals("", handler.getRequestLine());
    }

    @Test
    public void testParseEasyGetRequest() {
        String rawRequest = "GET / HTTP/1.1\r\n\r\n";
        handler.setRawRequest(rawRequest);
        handler.parseRequest();

        assertEquals("GET / HTTP/1.1", handler.getRequestLine());
    }

    @Test
    public void testParseAnotherGetRequest() {
        String rawRequest = "GET / HTTP/1.1\r\nHost: [rsid].112.2o7.net\r\n\r\n";
        handler.setRawRequest(rawRequest);
        handler.parseRequest();

        assertEquals("GET / HTTP/1.1\r\nHost: [rsid].112.2o7.net", handler.getRequestLine());
    }

    @Test
    public void testParseRequestWithBody() {
        String rawRequest = "POST / HTTP/1.1\r\nhost: https://importexport.amazonaws.com\r\n\r\nAction=GetStaHA256&JobId=JOB8rw%3D&";
        handler.setRawRequest(rawRequest);
        handler.parseRequest();

        assertEquals("Action=GetStaHA256&JobId=JOB8rw%3D&", handler.getRequestBody());
    }
}