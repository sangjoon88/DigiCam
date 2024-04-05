package collyTalk;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	public ChatClient(String userId) {
		this.userId = userId;
		setTitle("채팅 클라이언트");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		// 상단 패널: 서버 접속 정보 입력과 접속 버튼
		JPanel connectionPanel = new JPanel(new FlowLayout());
		ipField = new JTextField("192.168.0.91", 10); // 기본값으로 localhost 설정
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
		JScrollPane chatScrollPane = new JScrollPane(chatArea);
		chatPanel.add(chatScrollPane, BorderLayout.CENTER);

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
									ChatMsg msg = new ChatMsg(ChatMsg.PROT_IMAGE, userId, "이미지 전송");
									msg.setImage(icon);
									outStream.writeObject(msg);
									outStream.flush();
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
								userList.add(newUser);
								updateUserList();
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

	private void sendMessage() {
	    String message = messageField.getText();
	    if (!message.isEmpty()) {
	        if (message.startsWith("/w ")) {
	            String[] parts = message.split(" ", 3);
	            if (parts.length == 3) {
	                String targetUserId = parts[1];
	                String whisperMessage = parts[2];
	                String formattedMessage = userId + " -> " + targetUserId + " [" + getCurrentTimestamp() + "] : " + whisperMessage;
	                out.println(formattedMessage);
	            } else {
	                appendToChatArea("귓속말 명령어 형식이 잘못되었습니다. 예: /w 대상사용자ID 메시지");
	            }
	        } else {
	            String formattedMessage = userId + " [" + getCurrentTimestamp() + "] : " + message;
	            out.println(formattedMessage);
	            appendToChatArea(formattedMessage);
	        }
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

	private String getCurrentTimestamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		return dateFormat.format(currentTime);
	}

	private void appendToChatArea(String message) {
		chatArea.append(message + "\n");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ChatClient("");
			}
		});
	}
}
