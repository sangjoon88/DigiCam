package tcp.chat.multi;

import java.io.*;
import java.net.*;

      
class ReceiverThread extends Thread {     					            // 서버에서 클라이언트로부터 메시지를 수신하는 스레드
    Socket socket; 														// 클라이언트 소켓

    ReceiverThread(Socket socket) {											 // ReceiverThread 생성자: 소켓을 받아 초기화한다.
        this.socket = socket;
    }


    public void run() {									          // 스레드 실행 메서드
        try {
            BufferedReader reader = 
            new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓의 입력 스트림을 이용하여 클라이언트로부터 메시지를 수신하기 위한 BufferedReader 생성
            while (true) { 																	// 무한 루프를 통해 지속적으로 메시지를 수신한다.
            																					// 서버로부터 수신된 메시지를 모니터로 출력
                String str = reader.readLine();																			 // 서버로부터 한 줄씩 메시지를 수신
                if (str == null) 													// 만약 수신된 메시지가 null이라면, 연결이 끊어진 것으로 판단하여 루프를 탈출한다.
                    break;
                System.out.println(str); 												// 수신된 메시지를 모니터로 출력
            }
        }
        catch (IOException e) { 
            System.out.println(e.getMessage()); 
        }
    }
}
