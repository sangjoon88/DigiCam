import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private static final int PORT = 9002;
    private static HashSet<String> names = new HashSet<>();
    private static HashSet<PrintWriter> clients = new HashSet<>();

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
        private String name;
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String userName = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                userName = in.readLine();
                synchronized (names) {
                    names.add(userName);
                    // 클라이언트에게 현재 접속한 모든 클라이언트의 이름을 전송
                    for (String clientName : names) {
                        if (!clientName.equals(userName)) { // 자신의 아이디는 제외하고 전송
                            out.println("newUser:" + clientName);
                        }
                    }
                }
                clients.add(out);
                broadcast("Server: " + userName + "님이 접속하셨습니다.");
                broadcast("공지사항: "+ getNotice());

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("수신: " + message);
                    broadcast(message);
                }
            } catch (IOException e) {
                System.out.println("클라이언트와의 연결이 끊어졌습니다.");
            } finally {
                if (userName != null) {
                    synchronized (names) {
                        names.remove(userName);
                    }
                    System.out.println(userName + "님이 나가셨습니다.");
                    clients.remove(out);
                    broadcast("Server : " + userName + " 님이 나가셨습니다.");
                    updateClientList(); // 퇴장 시 클라이언트 목록 업데이트
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getNotice() {
            String notice = "";

            try {
                // JDBC 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");

                // DB 연결
                Connection conn = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/collytalk?serverTimezone=UTC", "root", "qwe123!@#");

                // 입력된 아이디와 날짜를 이용하여 데이터베이스에서 정보를 조회
                String sql = "select notice_content from notice_log order by notice_date desc limit 1;";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    notice = rs.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce) {
                ce.printStackTrace();
            }
            return notice;
        }

        private void broadcast(String message) {
            for (PrintWriter client : clients) {
                client.println(message);
            }
        }

        private void updateClientList() {
            StringBuilder userList = new StringBuilder();
            for (String clientName : names) {
                userList.append(clientName).append("\n");
            }
            broadcast("userList:" + userList.toString()); // 클라이언트 목록 전송
        }
    }
}
