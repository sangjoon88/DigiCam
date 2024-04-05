package collyTalk;  // 이미지 올리는 거 작업 중

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import  javax.swing.filechooser.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class RegisterScreen extends JFrame {
    private JLabel idLabel, nameLabel, passwordLabel, phoneLabel, profileLabel;
    private JTextField idField, nameField, phoneField;
    private JPasswordField passwordField;
    private JButton registerButton, uploadButton;
    
    public String imagePath;  										//여기 수정

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
                JFileChooser fileChooser = new JFileChooser();
                
                // 파일 선택 대화상자 필터 설정 (이미지 파일만 선택 가능하도록)
                fileChooser.setFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif"));		// 여기 수정
                
                // 파일 선택 대화상자를 표시하고 사용자가 이미지 파일을 선택한 경우
                if (fileChooser.showOpenDialog(RegisterScreen.this) == JFileChooser.APPROVE_OPTION) {
                    // 선택된 파일의 경로를 가져와서 프로필 사진 필드에 표시
                    imagePath = fileChooser.getSelectedFile().getPath();
                    profileLabel.setText("프로필 사진: " + imagePath);
                }
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

                    File imgfile = new File(imagePath);                                      // 여기 수정
             
                    FileInputStream fis = new FileInputStream(imgfile);					// 여기 수정
                 
                    
                    String sql = "INSERT INTO user (user_ID, user_name, user_pw, user_hp, user_pic, status) VALUES (?, ?, ?, ?, ?, '1')";  // 여기 수정
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, username);
                    pstmt.setString(2, name);
                    pstmt.setString(3, password);
                    pstmt.setString(4, phone);
                    pstmt.setBinaryStream(5, fis, (int) imgfile.length());

                    int rowsAffected = pstmt.executeUpdate();
 
                    
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
                } catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
