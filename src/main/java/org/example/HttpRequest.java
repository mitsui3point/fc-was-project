package org.example;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * HttpRequest
 *      RequestLine
 *      - HttpMethod
 *      - path
 *      - queryString
 *      Header
 *      Body
 * HTTProtocol(규약)에 맞게 split 을 해서 원하는 자원에 접근하여 원하는 결과값을 얻어온다.
 */
public class HttpRequest {
    private final RequestLine requestLine;
    //private final HttpHeaders httpHeaders;
    //private final Body body;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());
    }

    public boolean isGetRequest() {
        return "GET".equals(this.requestLine.getMethod());
    }

    public boolean matchPath(String path) {
        return this.requestLine.getUrlPath().equals(path);
    }

    public QueryStrings getQueryStrings() {
        return this.requestLine.getQueryString();
    }
}
