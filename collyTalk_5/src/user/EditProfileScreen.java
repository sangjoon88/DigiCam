package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class EditProfileScreen extends JFrame {
    private String userId;
	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField hpField;
	
    // MySQL 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/collytalk";
    private static final String USER = "root";
    private static final String PASSWORD = "qwe123!@#";

	/**
	 * Create the application.
	 */
	public EditProfileScreen(String userId) {
        this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("KollyTalk");
        frame.setSize(500, 700);
		frame.setBounds(100, 100, 500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(34, 77, 96));
		panel.setBounds(0, 0, 484, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel mainLogo = new JLabel("");
		mainLogo.setIcon(new ImageIcon("C:\\images\\kollytalk_logo.jpg"));
		mainLogo.setBounds(0, 0, 484, 162);
		panel.add(mainLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(34, 77, 96));
		panel_1.setBounds(0, 194, 484, 345);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel pwdLabel = new JLabel("변경할 비밀번호 :");
		pwdLabel.setForeground(new Color(255, 255, 255));
		pwdLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		pwdLabel.setBounds(12, 46, 175, 54);
		panel_1.add(pwdLabel);
		
		JLabel nameLabel = new JLabel("변경할 이름 :");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		nameLabel.setBounds(51, 100, 175, 54);
		panel_1.add(nameLabel);
		
		JLabel hpLabel = new JLabel("변경할 연락처 :");
		hpLabel.setForeground(new Color(255, 255, 255));
		hpLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		hpLabel.setBounds(33, 152, 151, 54);
		panel_1.add(hpLabel);
		
		JLabel msgLabel = new JLabel("상태 메세지 변경 :");
		msgLabel.setForeground(new Color(255, 255, 255));
		msgLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		msgLabel.setBounds(12, 202, 175, 64);
		panel_1.add(msgLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 60, 282, 34);
		panel_1.add(passwordField);
		
		nameField = new JTextField();
		nameField.setBounds(190, 110, 282, 34);
		panel_1.add(nameField);
		nameField.setColumns(10);
		
		hpField = new JTextField();
		hpField.setBounds(190, 165, 282, 34);
		panel_1.add(hpField);
		hpField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(190, 213, 282, 122);
		panel_1.add(textArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(34, 77, 96));
		panel_2.setBounds(0, 549, 484, 91);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton finBtn = new JButton("");
		finBtn.setBackground(new Color(34, 77, 96));
		finBtn.setIcon(new ImageIcon("C:\\images\\infofin_un.jpg"));
		finBtn.setPressedIcon(new ImageIcon("C:\\images\\infofin_click.jpg"));
		finBtn.setBounds(135, 10, 243, 71);
		finBtn.setBorderPainted(false);
		
		finBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String newPassword = passwordField.getText().trim();
	                String newName = nameField.getText().trim();
	                String newPhone = hpField.getText().trim();

	                // 사용자 정보를 업데이트하는 메서드 호출
	                updateUserProfile(newPassword, newName, newPhone);

	                // 수정이 완료되면 창을 닫음
	                dispose();
	            }
	        });
		
		panel_2.add(finBtn);
        frame.setVisible(true);
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
        frame.setVisible(false);
    }
}