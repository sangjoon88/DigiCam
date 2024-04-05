package collyTalk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserMenuScreen extends JFrame {
    private JButton enterChatRoomButton, editProfileButton;
    private String userId;

    public UserMenuScreen(String userId) {
        this.userId = userId;
        setTitle("사용자 메뉴");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        enterChatRoomButton = new JButton("채팅방 입장");
        enterChatRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 채팅방 입장 버튼 눌렀을 때의 동작
                ChatClient chatClient = new ChatClient(userId);
                chatClient.setVisible(true); // ChatClient 객체 표시
            }
        });
        panel.add(enterChatRoomButton);

        editProfileButton = new JButton("내 정보 변경");
        editProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 내 정보 변경 버튼 눌렀을 때의 동작
                // 여기에 내 정보 변경 화면으로 이동하는 코드를 작성하세요
            }
        });
        panel.add(editProfileButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserMenuScreen("");
            }
        });
    }
}