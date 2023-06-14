package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);


    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        //port 로 server socket 을 생성
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            // server socket 이 client socket 을 기다리게 한다.
            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

                /**
                 * step1 - 사용자 요청을 메인 Thread 가 처리하도록 한다.
                 */
                try (InputStream in = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(os);

                    HttpRequest httpRequest = new HttpRequest(br);
                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = new Calculator().calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse httpResponse = new HttpResponse(dos);
                        httpResponse.response200Header("application/json", body.length);
                        httpResponse.responseBody(body);
                    }
                }
            }
        }
    }
}
