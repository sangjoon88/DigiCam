package admin;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLoginScreen extends JFrame {
	private JLabel idLabel, passwordLabel;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton, adminLoginButton, exitButton;
	private JLabel lblImage;

	public AdminLoginScreen() {
		setTitle("로그인");
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

		idLabel = new JLabel("    관리자아이디: ");
		idLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정

		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(200, 30)); // 아이디 텍스트 필드의 크기 설정

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(idField, gbc);

		passwordLabel = new JLabel("관리자비밀번호: ");
		passwordLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(passwordLabel, gbc);
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 30)); // 비밀번호 텍스트 필드의 크기 설정
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(passwordField, gbc);

		loginButton = new JButton("로그인");
		loginButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		loginButton.setPreferredSize(new Dimension(150, 30)); // 로그인 버튼의 크기 설정
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panel.add(loginButton, gbc);

		exitButton = new JButton("나가기");
		exitButton.setPreferredSize(new Dimension(150, 30)); // 나가기 버튼의 크기 설정
		exitButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		panel.add(exitButton, gbc);

		add(panel);
		setVisible(true);

		// 로그인 버튼 리스너
		loginButton.addActionListener(new ActionListener() {
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

		// 나가기 버튼 리스너
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 나가기 버튼 눌렀을 때의 동작
				System.exit(0); // 프로그램 종료
			}
		});
	}

	public static void main(String[] args) {
		new AdminLoginScreen();
	}
}