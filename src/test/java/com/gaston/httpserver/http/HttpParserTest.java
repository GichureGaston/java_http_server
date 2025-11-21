package com.gaston.httpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    private HttpParser httpParser;
    @BeforeAll
    public  void beforeClass(){
        httpParser = new HttpParser();
    }

    @Test
    void parseHttpRequest() {
        HttpRequest request = null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidGETTestCase()
            );
        } catch (HttpParsingException e) {
            fail();
        }

        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals(request.getRequestTarget(), "/");
    }
    @Test
    void parseHttpRequestBadMethod1() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName1()
            );
            fail();
        } catch (HttpParsingException e) {
        assertEquals(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST, e.getErrorCode());        }
    }
    @Test
    void parseHttpRequestBadMethod2() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseMethodName2()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST, e.getErrorCode());        }
    }

    @Test
    void parseHttpInvalidNoItems() {
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineInvalidNumItems()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST, e.getErrorCode());       }
    }

    @Test
    void parseHttpEmptyRequestLine (){
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseEmptyRequestLine()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST, e.getErrorCode());       }
    }
    @Test
    void parseHttpOnlyCR(){
        try {
            HttpRequest request = httpParser.parseHttpRequest(
                    generateBadTestCaseRequestLineOnlyCR()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST, e.getErrorCode());       }
    }
    private InputStream generateValidGETTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:8000\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:143.0) Gecko/20100101 Firefox/143.0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Sec-GPC: 1\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "DNT: 1\r\n" +
                "Priority: u=0, i\r\n" +
                "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }
    private InputStream generateBadTestCaseMethodName1(){
        String rawData =
                "Host: localhost:8000\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }
    private InputStream generateBadTestCaseMethodName2(){
        String rawData ="GETTT/ HTTP/1.1\r\n"+
                "Host: localhost:8000\r\n" +
                        "Accept-Language: en-US,en;q=0.5\r\n" +
                        "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }


    private InputStream generateBadTestCaseRequestLineInvalidNumItems(){
        String rawData ="GETTT/ AAAAAA HTTP/1.1\r\n"+
                "Host: localhost:8000\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }
    private InputStream generateBadTestCaseEmptyRequestLine(){
        String rawData ="\r\n"+
                "Host: localhost:8000\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }
    private InputStream generateBadTestCaseRequestLineOnlyCR(){
        String rawData = "GET / HTTP/1.1\r" +
                "Host: localhost:8000\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:143.0) Gecko/20100101 Firefox/143.0\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                "Accept-Language: en-US,en;q=0.5\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Priority: u=0, i\r\n" +
                "\r\n";
        return new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
    }

}