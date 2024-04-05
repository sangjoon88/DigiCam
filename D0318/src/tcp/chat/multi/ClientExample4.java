package tcp.chat.multi;

import java.net.*;

class ClientExample4 {
    public static void main(String[] args) {

        if (args.length != 1) { 											// 사용 방법에 대한 예외 처리 ????
            System.out.println(
                "Usage: java ClientExample4 <user-name>");
            return;
        }
        try {
        																		// 서버와의 연결을 시도
            Socket socket = new Socket("192.168.0.91", 9002);

            // 메시지 송신 스레드와 수신 스레드를 생성하고 시작
            Thread thread1 = new SenderThread(socket, args[0]);		 // 송신 스레드 생성 및 시작
            Thread thread2 = new ReceiverThread(socket);				 // 수신 스레드 생성 및 시작

            thread1.start();													 // 송신 스레드 시작
            thread2.start(); 														// 수신 스레드 시작
        }
        catch (Exception e) { 
            System.out.println(e.getMessage()); 
        }
    }
}
