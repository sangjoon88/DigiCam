// PerClinetThread 클래스: 각 클라이언트 접속에 대해 하나씩 작동하는 스레드 클래스
package tcp.chat.multi;

import java.io.*;
import java.net.*;
import java.util.*;


class PerClinetThread extends Thread {
    // ArrayList 객체를 여러 스레드가 안전하게 공유할 수 있는 동기화된 리스트로 만듭니다.
    static List<PrintWriter> list = Collections.synchronizedList(new ArrayList<PrintWriter>());

    Socket socket; 					// 클라이언트 소켓
    PrintWriter writer; 					// 출력 스트림
    PerClinetThread(Socket socket) {
        this.socket= socket;
        try {
            writer = new PrintWriter(socket.getOutputStream()); 		// 클라이언트 소켓의 출력 스트림을 이용하여 PrintWriter를 생성
            list.add(writer); 														// 생성된 PrintWriter를 리스트에 추가
        }
        catch (Exception e) { 
            System.out.println(e.getMessage()); 
        }
    }
    
    										
    public void run() {											// 스레드 실행 메서드
        String name = null; 									// 대화명 변수 초기화
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())); 	// 클라이언트 소켓의 입력 스트림을 이용하여 BufferedReader 생성

            // 수신된 첫번째 문자열을 대화명으로 사용하기 위해 저장
            name = reader.readLine(); 										// 대화명을 읽어들임

            sendAll("#" + name + "님이 들어오셨습니다"); 				// 모든 클라이언트에게 새로운 사용자가 입장했다는 메시지를 전송

            while (true) {
                String str = reader.readLine(); 					// 클라이언트로부터 메시지를 읽어들임
                if (str == null) 											// 읽어들인 메시지가 null이면 루프를 종료
                    break;
                sendAll(name + ">" + str);						 // 모든 클라이언트에게 수신된 메시지를 송신 (대화명과 함께)
            }
        }
        catch (Exception e) { 											// 예외 처리
            System.out.println(e.getMessage()); 						// 예외 메시지 출력
        }
        finally { 														// 스레드 종료 시 처리할 작업
            list.remove(writer); 										// 리스트에서 해당 PrintWriter를 제거
            sendAll("#" + name + "님이 나가셨습니다"); 		// 사용자가 채팅을 종료했다는 메시지를 모든 클라이언트로 전송
            try {
                socket.close(); // 소켓 닫기
            }
            catch (Exception ignored) { 
            }
        }
    }
    
    // 서버에 연결되어 있는 모든 클라이언트로 똑같은 메시지를 보냅니다.
    private void sendAll(String str) {
        for (PrintWriter writer : list) { 										// 리스트에 있는 모든 PrintWriter에게 메시지 전송
            writer.println(str); 													// 메시지 전송
            writer.flush(); 														// 버퍼 비우기
        }
    }
}
