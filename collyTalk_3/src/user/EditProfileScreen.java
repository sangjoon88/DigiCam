package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditProfileScreen extends JFrame {
    private JTextField passwordField, nameField, phoneField;
    private JLabel passwordLabel, nameLabel, phoneLabel;
    private JButton saveButton;
    private String userId;

    // MySQL 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collytalk";
    private static final String USER = "root";
    private static final String PASSWORD = "qwe123!@#";

    public EditProfileScreen(String userId) {
        this.userId = userId;

        setTitle("내 정보 변경");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        passwordLabel = new JLabel("새로운 비밀번호:");
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        nameLabel = new JLabel("새로운 이름:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        phoneLabel = new JLabel("새로운 핸드폰 번호:");
        phoneField = new JTextField();
        panel.add(phoneLabel);
        panel.add(phoneField);

        saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newPassword = passwordField.getText().trim();
                String newName = nameField.getText().trim();
                String newPhone = phoneField.getText().trim();

                // 사용자 정보를 업데이트하는 메서드 호출
                updateUserProfile(newPassword, newName, newPhone);

                // 수정이 완료되면 창을 닫음
                dispose();
            }
        });
        panel.add(saveButton);

        add(panel);
        setVisible(true);
    }

    // 사용자 정보를 업데이트하는 메서드
    private void updateUserProfile(String newPassword, String newName, String newPhone) {
        try {
            // JDBC를 사용하여 MySQL 데이터베이스에 연결
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // UPDATE 쿼리를 준비
            String sql = "UPDATE user SET USER_PW = IFNULL(?, USER_PW), USER_NAME = IFNULL(?, USER_NAME), USER_HP = IFNULL(?, USER_HP) WHERE USER_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 쿼리의 매개변수 설정
            pstmt.setString(1, newPassword.isEmpty() ? null : newPassword);
            pstmt.setString(2, newName.isEmpty() ? null : newName);
            pstmt.setString(3, newPhone.isEmpty() ? null : newPhone);
            pstmt.setString(4, userId);

            // 쿼리 실행
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "사용자 정보가 성공적으로 업데이트되었습니다.");
            } else {
                JOptionPane.showMessageDialog(this, "사용자 정보 업데이트에 실패했습니다.");
            }

            // 리소스 정리
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터베이스 오류: " + ex.getMessage());
        }
    }
}