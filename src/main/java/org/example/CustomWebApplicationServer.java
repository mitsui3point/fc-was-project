package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                 * Step2 - 사용자 요청이 들어올 때 마다 Thread 를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 */
                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }
    }
}
