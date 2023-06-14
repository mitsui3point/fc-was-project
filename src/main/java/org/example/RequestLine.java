package org.example;

import java.util.Objects;

public class RequestLine {

    /**
     * GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
     */
    private final String method; // GET
    private final String urlPath; // /calculate
    private String queryString; // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryString = queryString;
    }

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        String[] urlPathTokens = tokens[1].split("\\?");

        this.method = tokens[0];
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryString = urlPathTokens[1];
        }
    }

    public QueryStrings getQueryString() {
        return new QueryStrings(this.queryString);
    }

    public Object getMethod() {
        return this.method;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryString, that.queryString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryString);
    }
}