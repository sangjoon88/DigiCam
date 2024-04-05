package collyTalk;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServer {
    private static final int PORT = 9002;
    private static Map<String, PrintWriter> clients = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("채팅 서버 시작...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("서버 오류: " + e.getMessage());
        }
    }

    private static class Handler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientId;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // 클라이언트가 처음으로 보내는 메시지에서 아이디 추출
                clientId = in.readLine();
                clients.put(clientId, out); // 클라이언트 아이디를 저장

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("수신: " + message);
                    this.broadcast(message, clientId);
                }
            } catch (IOException e) {
                System.out.println("클라이언트와의 연결이 끊어졌습니다.");
            } finally {
                if (out != null) {
                    clients.remove(clientId);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message, String senderId) {
            if (message.contains(" -> ")) {
                String[] parts = message.split(" -> ", 2);
                String senderUserId = parts[0];
                String[] receiverParts = parts[1].split(" ", 2);
                String receiverUserId = receiverParts[0];
                String whisperMessage = parts[1];
                
                PrintWriter receiverClient = clients.get(receiverUserId);
                PrintWriter senderClient = clients.get(senderUserId);
                
                if (receiverClient != null && senderClient != null) {
                    receiverClient.println("귓속말 from " + whisperMessage);
                    senderClient.println("귓속말 to " + receiverUserId + ": " + receiverParts[1]);
                } else {
                    if (senderClient != null) {
                        senderClient.println("대상 사용자가 온라인 상태가 아닙니다.");
                    }
                }
            } else {
                for (PrintWriter client : clients.values()) {
                    if (!clientId.equals(senderId) || client != out) {
                        client.println(message);
                    }
                }
            }
        }
    }
}
