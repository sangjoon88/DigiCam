import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.AdminLoginScreen;
import user.RegisterScreen;

class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}

public class LoginScreen extends JFrame {

	public LoginScreen() {
		setTitle("로그인");
		setSize(500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel backgroundPanel = new ImagePanel(new ImageIcon("images/background2.jpg").getImage());
		backgroundPanel.setLayout(new BorderLayout());
		setContentPane(backgroundPanel);
		
        // 로고 이미지를 상단에 추가합니다.
        JLabel logoLabel = new JLabel(new ImageIcon("images/kollytalk.jpg"));
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // 로고 이미지를 가운데 정렬합니다.

		// 중앙에 위치할 패널을 생성하여 GridBagLayout을 설정합니다.
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false); // 중앙 패널을 투명하게 만듭니다.
		GridBagConstraints gbc = new GridBagConstraints();
		
		// 아이디 레이블과 필드
        JLabel idLabel = new JLabel("아이디:");
        idLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
        JTextField idField = new JTextField(15);
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(idField, gbc);

        // 비밀번호 레이블과 필드
        JLabel passwordLabel = new JLabel("비밀번호:");
		passwordLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(passwordField, gbc);

        // 로그인 버튼
        JButton loginButton = new JButton("로그인");
		loginButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(loginButton, gbc);

        // 나머지 버튼들
        JButton registerButton = new JButton("회원가입");
		registerButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
        JButton adminLoginButton = new JButton("관리자 로그인");
        JButton exitButton = new JButton("나가기");
        gbc.gridy = 3;
        centerPanel.add(registerButton, gbc);
        gbc.gridy = 4;
        centerPanel.add(adminLoginButton, gbc);
        gbc.gridy = 5;
        centerPanel.add(exitButton, gbc);

        backgroundPanel.add(logoLabel, BorderLayout.NORTH);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
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

		// 회원가입 버튼 리스너
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼 눌렀을 때의 동작
				dispose(); // 현재 창 닫기
				new RegisterScreen(); // 회원가입 화면 열기
			}
		});

		// 관리자 로그인 버튼 리스너
		adminLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 관리자 로그인 버튼 눌렀을 때의 동작
				new AdminLoginScreen();
				// 여기에 관리자 로그인 처리 코드를 작성하세요
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
		new LoginScreen();
	}
}