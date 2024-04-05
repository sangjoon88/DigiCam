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
import java.util.HashMap;
import java.util.HashSet;

public class ChatServer {
	private static final int PORT = 9002;
	private static String chatRder = null;
	private static HashMap<String, PrintWriter> clients = new HashMap<>();

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

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			String userName = null;
			String koutUser = null;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				userName = in.readLine();
				if (clients.size() == 0) {
					chatRder = userName;
				}
				clients.put(userName, out);
				broadcast("Server: " + userName + "님이 접속하셨습니다.");
				broadcast("공지사항: " + getNotice());

				String message;
				while ((message = in.readLine()) != null) {
					System.out.println("수신: " + message);
					if (message.startsWith("whisper")) {
						int firstIdx = message.indexOf(":");
						int secondIdx = message.indexOf("->");
						int thirdIdx = message.indexOf(" ", secondIdx + 3);
						String sendUserNm = message.substring(firstIdx + 1, secondIdx - 1);
						String recvUserNm = message.substring(secondIdx + 3, thirdIdx);
						String onlyMsg = message.substring(thirdIdx + 1);
						PrintWriter sPw = clients.get(sendUserNm);
						PrintWriter rPw = clients.get(recvUserNm);
						if (sPw != null) {
							sPw.println("                                                       귓속말 to " + recvUserNm
									+ onlyMsg); 
						}
						if (rPw != null) {
							rPw.println("귓속말 from " + sendUserNm + onlyMsg);
						}
					} else if (message.startsWith("newUser:")) {
						updateUser();
					} else if (message.startsWith("kickout")) {
						int firstIdx = message.indexOf(":");
						String user = message.substring(firstIdx + 1);
						String[] users = user.split(" -> ");
						if (users[0].equals(chatRder) == false) {
							System.out.printf("%s는 방장이 아닙니다. 강퇴 기능을 실행할수 없습니다.%n", users[0]);
						} else {
							PrintWriter pw = clients.get(users[1]);
							if (pw != null) {
								koutUser = users[1];
								String msg = String.format("[kickout] %s 님은 강퇴되었습니다.", users[1]);
								pw.println(msg);
								throw new IOException(msg);
							}
						}

					} else {
						broadcast(message);
					}
				}
			} catch (IOException e) {
				System.out.println("클라이언트와의 연결이 끊어졌습니다.");
			} finally {
				if (koutUser != null) {
					System.out.println(koutUser + "님이 나가셨습니다.");
					broadcast("Server : " + koutUser + " 님이 나가셨습니다.");
					clients.remove(koutUser);
					updateUser();
					koutUser = null;
				} else {
					System.out.println(userName + "님이 나가셨습니다.");
					broadcast("Server : " + userName + " 님이 나가셨습니다.");
					clients.remove(userName);
					updateUser();
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
			for (PrintWriter client : clients.values()) {
				client.println(message);
			}
		}

		private void updateUser() {
			String totalUser = "";
			for (String user : clients.keySet()) {
				totalUser += user + ",";
			}
			broadcast("newUser:" + totalUser.substring(0, totalUser.length() - 1));
		}
	}
}