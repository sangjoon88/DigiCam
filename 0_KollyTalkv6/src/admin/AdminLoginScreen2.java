package admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AdminLoginScreen2 extends JFrame {

	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLoginScreen2 window = new AdminLoginScreen2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminLoginScreen2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("KollyTalk");
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
		mainLogo.setBounds(0, 40, 484, 165);
		panel.add(mainLogo);

		JLabel idLabel = new JLabel("관리자 아이디");
		idLabel.setForeground(new Color(255, 255, 255));
		idLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 22));
		idLabel.setBounds(40, 338, 140, 36);
		panel.add(idLabel);

		JLabel passwordLabel = new JLabel("관리자 비밀번호");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 22));
		passwordLabel.setBounds(20, 378, 165, 36);
		panel.add(passwordLabel);

		idField = new JTextField();
		idField.setBounds(187, 338, 200, 30);
		panel.add(idField);
		idField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(187, 378, 200, 30);
		panel.add(passwordField);
		passwordField.setColumns(10);

		JButton loginBtn = new JButton("");
		loginBtn.setIcon(new ImageIcon("C:\\images\\login_un.jpg"));
		loginBtn.setPressedIcon(new ImageIcon("C:\\images\\login_click.jpg"));
		loginBtn.setBounds(90, 432, 265, 57);
		loginBtn.setBorderPainted(false);
		panel.add(loginBtn);

		frame.setVisible(true);

		// 로그인 버튼 리스너
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 아이디와 비밀번호 입력 받기
				String username = idField.getText();
				String password = new String(passwordField.getPassword());

				// 여기에 로그인 처리 코드를 작성하세요
				try {
					// JDBC 연결
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root",
							"qwe123!@#");
					Statement stmt = conn.createStatement();

					// 입력된 아이디와 비밀번호를 이용하여 데이터베이스에서 정보를 조회
					String query = "SELECT * FROM admin_info WHERE admin_id='" + username + "' AND admin_pwd='"
							+ password + "'";
					ResultSet rs = stmt.executeQuery(query);

					if (rs.next()) {
						// 로그인 성공
						dispose(); // 현재 창 닫기 
						new AdminMenuScreen(username);////////// 관리자 메뉴로 넘어가기!!!!!!!!!
					} else {
						// 로그인 실패
						JOptionPane.showMessageDialog(null, "관리자 아이디 또는 비밀번호가 일치하지 않습니다.");
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

	}

}
