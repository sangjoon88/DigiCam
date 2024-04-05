package tcp.chat.multi;

import java.net.*;
import java.io.*;
                                         
class SenderThread extends Thread {   					 // SenderThread 클래스: 클라이언트에서 서버로 메시지를 전송하는 스레드
    Socket socket; 												// 클라이언트 소켓
    String name; 												// 클라이언트의 대화명

    SenderThread(Socket socket, String name) {          // SenderThread 생성자: 소켓과 대화명을 받아 초기화한다.
        this.socket = socket;
        this.name = name;
    }

    public void run() {																								   // 스레드 실행 메서드
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));      // 키보드 입력을 받기 위한 BufferedReader 생성
            PrintWriter writer = new PrintWriter(socket.getOutputStream());               // 소켓의 출력 스트림을 이용하여 서버에 메시지를 전송하기 위한 PrintWriter 생성
            
            // 제일 먼저 서버로 대화명 송신한다.
            writer.println(name);
            writer.flush();             // 버퍼 비우기
            
            // 키보드로 입력된 메시지를 서버로 송신 
            while (true) {                               // 무한 루프를 통해 지속적으로 메시지를 입력받고 전송한다.
                String str = reader.readLine();      // 키보드로부터 한 줄씩 입력 받음
                if (str.equals("bye"))                // 사용자가 bye를 입력하면 루프를 탈출하여 채팅 종료
                    break;
                writer.println(str);           // 서버에 메시지 전송
                writer.flush();                         // 버퍼 비우기
            }
        }
        catch (Exception e) { // 예외 처리
            System.out.println(e.getMessage()); 
        }
        finally { // 마지막에 실행되는 블록
            try { 
                socket.close(); // 소켓 닫기
            } 
            catch (Exception ignored) { // 예외 무시
            }
        }
    }
}
