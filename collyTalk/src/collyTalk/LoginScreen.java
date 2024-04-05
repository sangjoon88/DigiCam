package collyTalk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame {
    private JLabel idLabel, passwordLabel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, adminLoginButton, exitButton;

    public LoginScreen() {
        setTitle("로그인");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        idLabel = new JLabel("아이디:");
        panel.add(idLabel);
        idField = new JTextField();
        panel.add(idField);

        passwordLabel = new JLabel("비밀번호:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 눌렀을 때의 동작
                String username = idField.getText();
                String password = new String(passwordField.getPassword());

                // 여기에 로그인 처리 코드를 작성하세요
                try {
                    // JDBC 연결
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
                    Statement stmt = conn.createStatement();

                    // 입력된 아이디와 비밀번호를 이용하여 데이터베이스에서 정보를 조회
                    String query = "SELECT * FROM user WHERE user_id='" + username + "' AND user_pw='" + password + "'";
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        // 로그인 성공
                        dispose(); // 현재 창 닫기
                        new UserMenuScreen(username); // 사용자 메뉴 화면 열기 (사용자 ID 전달)
                    } else {
                        // 로그인 실패
                        JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.");
                    }

                    // 자원 해제
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
                }
            }
        });
        panel.add(loginButton);

        registerButton = new JButton("회원가입");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼 눌렀을 때의 동작
                dispose(); // 현재 창 닫기
                new RegisterScreen(); // 회원가입 화면 열기
            }
        });
        panel.add(registerButton);

        adminLoginButton = new JButton("관리자 로그인");
        adminLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 관리자 로그인 버튼 눌렀을 때의 동작
                // 여기에 관리자 로그인 처리 코드를 작성하세요
            }
        });
        panel.add(adminLoginButton);

        exitButton = new JButton("나가기");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 나가기 버튼 눌렀을 때의 동작
                System.exit(0); // 프로그램 종료
            }
        });
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
