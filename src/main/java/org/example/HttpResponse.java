package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class HttpResponse {
    public static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private final DataOutputStream dos;

    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
    }


    public void response200Header(String contentType, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n"); //status line
            dos.writeBytes("Content-Type: " + contentType + ";charset=utf-8" + "\r\n"); //header lines
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n"); //header lines
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void responseBody(byte[] body) {
        try {
            dos.write(body, 0 , body.length); //body line
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
