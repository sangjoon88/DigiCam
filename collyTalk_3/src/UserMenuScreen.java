import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import user.EditProfileScreen;

public class UserMenuScreen extends JFrame {
    private JButton enterChatRoomButton, editProfileButton;
    private String userId;
    private JLabel lblImage;
    
    public UserMenuScreen(String userId) {
        this.userId = userId;
        setTitle("사용자 메뉴");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblImage = new JLabel(new ImageIcon("images/kollytalk.jpg"));
        lblImage.setHorizontalAlignment(JLabel.CENTER); // 레이블의 이미지를 가운데 정렬

        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(lblImage, BorderLayout.NORTH); // 이미지 레이블을 상단에 추가

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        enterChatRoomButton = new JButton("채팅방 입장");
        enterChatRoomButton.setPreferredSize(new Dimension(50, 80)); // 채팅방 입장 버튼 크기 조정
        enterChatRoomButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
        enterChatRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 채팅방 입장 버튼 눌렀을 때의 동작
                ChatClient chatClient = new ChatClient(userId);
                chatClient.setVisible(true); // ChatClient 객체 표시
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(enterChatRoomButton, gbc);

        editProfileButton = new JButton("내 정보 변경");
        editProfileButton.setPreferredSize(new Dimension(50, 80)); // 내 정보 변경 버튼 크기 조정
        editProfileButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
        editProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 내 정보 변경 버튼 눌렀을 때의 동작
                EditProfileScreen editProfileScreen = new EditProfileScreen(userId);
                editProfileScreen.setVisible(true);
                // 여기에 내 정보 변경 화면으로 이동하는 코드를 작성하세요
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(editProfileButton, gbc);

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