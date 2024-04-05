package tcp.chat.multi;

import java.net.*;
                                 //다중 클라이언트를 받는 TCP 채팅 서버
class ServerExample4 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;                          // 서버 소켓 객체 선언 및 초기화
        try {
            serverSocket = new ServerSocket(9002);              // 9002 포트를 통해 서버 소켓 생성
            while (true) {                                               // 계속해서 클라이언트의 연결을 대기하는 무한 루프
                Socket socket = serverSocket.accept();            // 클라이언트의 연결을 수락하고 소켓 생성
                Thread thread = new PerClinetThread(socket);   // 각 클라이언트에 대한 스레드 생성
                thread.start();                                             // 스레드 실행
            }
        }
        catch (Exception e) {                                               // 예외 처리
            System.out.println(e.getMessage()); 							// 예외 메시지 출력
        }
    }
}