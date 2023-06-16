package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        logger.info("[CustomWebApplicationServer] new client {} started", Thread.currentThread().getName());
        /**
         * 메인 쓰레드에서 동작한다는 문제,
         * 만약 메인쓰레드가 해당 작업을 진행하는 도중 블로킹이 걸리게 된다면,
         * 메인 쓰레드가 작업을 끝낼때 까지 기다려야하는 상황이 발생,
         * 이때문에 메인 쓰레드가 아닌 별도 쓰레드로 작업을 진행하도록 로직 수정이 필요.
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
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
