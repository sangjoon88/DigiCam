package collyTalk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatClient extends JFrame {
    private JTextField ipField, portField, messageField;
    private JTextArea chatArea, userListArea;
    private JButton connectButton, sendButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ArrayList<String> userList = new ArrayList<>();
    private String userId;

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
            // 사용자 ID와 입력 시간 포함하여 메시지 구성
            String formattedMessage = userId + " [" + getCurrentTime() + "] : " + message;
            out.println(formattedMessage);
            messageField.setText("");
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
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