import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatClient extends JFrame {
	private JTextField ipField, portField, messageField;
	private JTextArea chatArea, userListArea;
	private JButton connectButton, sendButton;
	private Socket socket;
	private PrintWriter out;
	public ObjectOutputStream outStream;
	private BufferedReader in;
	private ArrayList<String> userList = new ArrayList<>();
	private String userId;
	private JButton btnFile = new JButton(); // 파일열기
	private JButton btnEmoticon = new JButton(); // 이모티콘
	private JPanel bottom = new JPanel();
	private int chatCnt = 0;

	// 이미지채팅용 AREA
	private DefaultListModel dlm = new DefaultListModel<>();
	private JList<DefaultListModel<String>> list = new JList(dlm);

	public ChatClient(String userId) {
		this.userId = userId;
		setTitle("채팅 클라이언트");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// 상단 패널: 서버 접속 정보 입력과 접속 버튼
		JPanel connectionPanel = new JPanel(new FlowLayout());
		ipField = new JTextField("localhost", 10); // 기본값으로 localhost 설정
		portField = new JTextField("9002", 5); // 기본값으로 8888 포트 설정
		connectButton = new JButton("접속");
		connectionPanel.add(new JLabel("IP:"));
		connectionPanel.add(ipField);
		connectionPanel.add(new JLabel("Port:"));
		connectionPanel.add(portField);
		connectionPanel.add(connectButton);

		// 중간 패널: 채팅 대화창
		JPanel chatPanel = new JPanel(new BorderLayout());
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatPanel.add(new JScrollPane(list), BorderLayout.CENTER);

		// 하단 패널: 대화 입력창과 전송 버튼
		JPanel inputPanel = new JPanel(new BorderLayout());
		messageField = new JTextField();
		sendButton = new JButton("전송");
		inputPanel.add(messageField, BorderLayout.CENTER);
		inputPanel.add(sendButton, BorderLayout.EAST);

		// 우측 패널: 접속자 목록
		JPanel userListPanel = new JPanel(new BorderLayout());
		userListArea = new JTextArea();
		userListArea.setEditable(false);
		JScrollPane userListScrollPane = new JScrollPane(userListArea);
		userListPanel.add(new JLabel("접속자 목록"), BorderLayout.NORTH);
		userListPanel.add(userListScrollPane, BorderLayout.CENTER);

		panel.add(connectionPanel, BorderLayout.NORTH);
		panel.add(chatPanel, BorderLayout.CENTER);
		panel.add(inputPanel, BorderLayout.SOUTH);
		panel.add(userListPanel, BorderLayout.EAST);

		// 이모티콘 버튼 초기화 및 하단 패널에 추가
		ImageIcon ii1 = new ImageIcon(
				new ImageIcon("emoticon/img1.png").getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
		btnEmoticon.setIcon(ii1);
		btnEmoticon.setToolTipText("이모티콘");
		inputPanel.add(btnEmoticon, BorderLayout.WEST);

		// Connect 버튼 리스너 등록
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectToServer();
			}
		});

		// Send 버튼 리스너 등록
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		// messageField에 KeyListener 추가
		messageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// 이모티콘 버튼에 대한 이벤트 리스너 등록
		btnEmoticon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog emoticonDlg = new JDialog();
				emoticonDlg.setSize(400, 320);
				emoticonDlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				emoticonDlg.setLayout(new BorderLayout());
				emoticonDlg.setTitle("이모티콘 선택");
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 4));

				File imgDir = new File("emoticon");
				File[] imgFiles = imgDir.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith("png") || name.endsWith("jpg") || name.endsWith("gif");
					}
				});
				for (int i = 0; i < imgFiles.length; i++) {
					ImageIcon icon = new ImageIcon(imgFiles[i].getAbsolutePath());
					JLabel lbl = new JLabel(icon);
					lbl.setSize(20, 20);
					lbl.addMouseListener(new MouseListener() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if (socket != null) {
								ImageIcon icon = (ImageIcon) lbl.getIcon();
								try {
									String imgTest = "/img " + icon;
									String formattedMessage = userId + " [" + getCurrentTimestamp() + "] : " + imgTest;
									out.println(formattedMessage);

									saveMessageToDatabase(userId, imgTest, getCurrentTimestamp()); // 일반 메세지 보내기
									System.out.println("초기화1: " + messageField.getText());
									messageField.setText("");
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(lbl, "서버와 연결되어 있지 않아 이미지를 전송하지 않습니다.");
							}
							emoticonDlg.dispose();
						}

						@Override
						public void mousePressed(MouseEvent e) {
						}

						@Override
						public void mouseExited(MouseEvent e) {
						}

						@Override
						public void mouseEntered(MouseEvent e) {
						}

						@Override
						public void mouseClicked(MouseEvent e) {
						}
					});
					panel.add(lbl);
				}
				emoticonDlg.add(panel, BorderLayout.CENTER);
				emoticonDlg.setResizable(false);
				emoticonDlg.setVisible(true);
			}
		});

		add(panel);
		setVisible(true);
	}

	private void connectToServer() {
		try {
			String serverIP = ipField.getText();
			int serverPort = Integer.parseInt(portField.getText());
			socket = new Socket(serverIP, serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 서버에 사용자 ID 전송
			out.println(userId);

			// 서버로부터 수신된 메시지를 출력하는 스레드 시작
			new Thread(new Runnable() {
				public void run() {
					String message;
					try {
						while ((message = in.readLine()) != null) {
							if (message.startsWith("newUser:")) {
								String newUser = message.substring(8);
								String[] arr = newUser.split(",");
								userList = new ArrayList<String>();
								for(String user : arr) {
									userList.add(user);									
								}
								updateUserList();
							}
							else if(message.startsWith("[kickout]")) {
								appendToChatArea(message);
								appendToChatArea("5초 후에 자동으로 채팅창이 닫힙니다.");
								try {
									Thread.sleep(5*1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								dispose();
							}
							else if (message.indexOf("] : /img") != -1) {

								String imgSearch = message.substring(message.indexOf("/img ") + 5);

								ImageIcon icon = new ImageIcon(imgSearch); // msg.getImage();
								Image image = icon.getImage();
								Image chgImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
								icon = new ImageIcon(chgImg);

								dlm.add(chatCnt++, icon);
							} else {
								appendToChatArea(message);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();

			appendToChatArea("서버에 연결되었습니다.");

			// 현재 사용자 정보를 서버에 전송하여 접속자 목록에 추가
			out.println("newUser:" + userId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateUserList() {
		StringBuilder userListText = new StringBuilder();
		for (String user : userList) {
			userListText.append(user).append("\n");
		}
		userListArea.setText(userListText.toString());
	}

	// 자신이 입력한 것만 출력
	private void sendMessage() {
		String message = messageField.getText();

		if (!message.isEmpty()) {
			
			checkKickOutWord(message); 

			if (message.startsWith("/w ")) { // 귓속말 명령어 확인
				String[] parts = message.split(" ",3); // "/w", "대상 사용자 ID", "메시지"로 분리
				if (parts.length == 3) {
					String targetUserId = parts[1];
					String whisperMessage = parts[2];
					String formattedMessage = userId + " -> " + targetUserId + " [" + getCurrentTimestamp() + "] : "
							+ whisperMessage;
					out.println("whisper:" + formattedMessage); // 서버에 귓속말 메시지 전송
				} else {
					appendToChatArea("귓속말 명령어 형식이 잘못되었습니다. 예: /w 대상사용자ID 메시지");
				}
			} else if(message.startsWith("/k")){
				String[] parts = message.split(" "); // "/w", "대상 사용자 ID", "메시지"로 분리
				if (parts.length > 1) {
					String targetUserId = parts[1];
					String formattedMessage = userId + " -> " + targetUserId ;
					out.println("kickout:" + formattedMessage); // 서버에 귓속말 메시지 전송
				} else {
					appendToChatArea("귓속말 명령어 형식이 잘못되었습니다. 예: /w 대상사용자ID 메시지");
				}
			} else { // 일반 메시지
				String formattedMessage = userId + " [" + getCurrentTimestamp() + "] : " + message;
				out.println(formattedMessage);
			}
			saveMessageToDatabase(userId, message, getCurrentTimestamp());
			System.out.println("초기화2: " + messageField.getText());
			messageField.setText("");
		}
	}

	private void saveMessageToDatabase(String userId, String message, String timestamp) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// DB 연결
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk?serverTimezone=UTC", "root",
					"qwe123!@#");

			// SQL 명령 실행
			String sql = "INSERT INTO chat_log VALUES (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, timestamp);
			pstmt.setInt(2, 1);
			pstmt.setString(3, message);
			pstmt.setString(4, userId);

			// SQL 명령 실행
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	private void displayNotice(String message) {
		appendToChatArea("notice: "+ message);
	}

	private String getCurrentTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		return dateFormat.format(currentTime);
	}

	private void appendToChatArea(String message) {
	    // 사용자가 보낸 메시지인지 여부를 판단하여 사용자 ID를 추가하여 표시
	    if (message.startsWith(userId)) {
	        dlm.add(chatCnt++, "                                                       "+message + "\n");
	    } else {
	        dlm.add(chatCnt++,message + "\n");
	    }
	}
	
	public void checkKickOutWord(String msg) {
		// JDBC 연결
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
			Statement stmt = conn.createStatement();
			// 모든 회원의 이름을 가져오는 쿼리
			String sql = "SELECT kickout_word FROM kickout";
			ResultSet rs = stmt.executeQuery(sql);

			// 모든 회원의 이름을 StringBuilder에 추가
			while (rs.next()) {
				String kickout_word = rs.getString("kickout_word");
				if (msg.contains(kickout_word)) {
					System.out.println("체크");
					System.exit(1);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ChatClient("");
			}
		});
	}
}