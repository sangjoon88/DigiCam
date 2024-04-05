package collyTalk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterScreen extends JFrame {
    private JLabel idLabel, nameLabel, passwordLabel, phoneLabel, profileLabel;
    private JTextField idField, nameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, uploadButton;

    public RegisterScreen() {
        setTitle("회원 가입");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        idLabel = new JLabel("ID:");
        panel.add(idLabel);

        idField = new JTextField();
        panel.add(idField);

        nameLabel = new JLabel("이름:");
        panel.add(nameLabel);

        nameField = new JTextField();
        panel.add(nameField);

        passwordLabel = new JLabel("비밀번호:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        phoneLabel = new JLabel("연락처:");
        panel.add(phoneLabel);

        phoneField = new JTextField();
        panel.add(phoneField);

        profileLabel = new JLabel("프로필 사진:");
        panel.add(profileLabel);

        uploadButton = new JButton("업로드");
        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 프로필 사진 업로드 버튼 클릭 시 동작하는 코드
                // 여기에 프로필 사진 업로드 기능을 구현하세요
            }
        });
        panel.add(uploadButton);

        registerButton = new JButton("회원 가입");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원 가입 버튼 클릭 시 동작하는 코드
                String username = idField.getText();
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText();
                // 프로필 사진은 나중에 처리할 수 있도록 코드를 추가하세요.
                
                // 여기에 회원 가입 처리 코드를 작성하세요
                try {
                    // JDBC 연결
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
                    Statement stmt = conn.createStatement();

                    // 사용자 정보를 user 테이블에 삽입하는 SQL 쿼리
                    String sql = "INSERT INTO user (user_ID, user_name, user_pw, user_hp, user_pic, status) VALUES "
                    		+ "('" + username + "', '" + name + "', '" + password + "', '" + phone + "', 'profile_pic_path', '1')";
                    
                    // SQL 쿼리 실행
                    int rowsAffected = stmt.executeUpdate(sql);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.");
                    }
                    
                    // 자원 해제
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
                }
            }
        });
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterScreen();
    }
}
