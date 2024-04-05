package user;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RegisterScreen extends JFrame {

	private JFrame frame;
	private JTextField idField;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JTextField hpField;
	public String imagePath;

	/**
	 * Create the application.
	 */
	public RegisterScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("KollyTalk");
		frame.setSize(500,700);
		frame.setBounds(100, 100, 510, 729);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(34, 77, 96));
		panel.setBounds(0, 0, 500, 700);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel mainLogo = new JLabel(
				new ImageIcon("C:\\images\\kollytalk_logo.jpg"));
		mainLogo.setBounds(12, 10, 476, 165);
		panel.add(mainLogo);

		JLabel idLabel = new JLabel("ID :");
		idLabel.setForeground(new Color(255, 255, 255));
		idLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		idLabel.setBounds(70, 290, 62, 62);
		panel.add(idLabel);

		JLabel nameLabel = new JLabel("이름 :");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		nameLabel.setBounds(70, 345, 75, 27);
		panel.add(nameLabel);

		JLabel passwordLabel = new JLabel("비밀번호 :");
		passwordLabel.setForeground(new Color(255, 255, 255));
		passwordLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		passwordLabel.setBounds(70, 385, 101, 27);
		panel.add(passwordLabel);

		JLabel hpLabel = new JLabel("연락처 :");
		hpLabel.setForeground(new Color(255, 255, 255));
		hpLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		hpLabel.setBounds(70, 420, 118, 38);
		panel.add(hpLabel);

		JLabel picLabel = new JLabel("프로필 사진 :");
		picLabel.setForeground(new Color(255, 255, 255));
		picLabel.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		picLabel.setBounds(70, 470, 113, 40);
		panel.add(picLabel);

		idField = new JTextField();
		idField.setBounds(123, 305, 256, 30);
		panel.add(idField);
		idField.setColumns(10);

		nameField = new JTextField();
		nameField.setBounds(133, 345, 246, 30);
		panel.add(nameField);
		nameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(167, 385, 212, 30);
		panel.add(passwordField);

		hpField = new JTextField();
		hpField.setBounds(167, 425, 212, 30);
		panel.add(hpField);
		hpField.setColumns(10);

		JButton updBtn = new JButton("");
		updBtn.setBackground(new Color(34, 77, 96));
		updBtn.setIcon(new ImageIcon("C:\\images\\upload_un.jpg"));
		updBtn.setPressedIcon(new ImageIcon("C:\\images\\upload_click.jpg"));
		updBtn.setBounds(191, 470, 188, 40);
		updBtn.setBorderPainted(false);
		updBtn.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
             JFileChooser fileChooser = new JFileChooser();
             
             // 파일 선택 대화상자 필터 설정 (이미지 파일만 선택 가능하도록)
             fileChooser.setFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif"));		// 여기 수정
             
             // 파일 선택 대화상자를 표시하고 사용자가 이미지 파일을 선택한 경우
             if (fileChooser.showOpenDialog(RegisterScreen.this) == JFileChooser.APPROVE_OPTION) {
                 // 선택된 파일의 경로를 가져와서 프로필 사진 필드에 표시
                 imagePath = fileChooser.getSelectedFile().getPath();
             }
         }
     });
		
		panel.add(updBtn);

		JButton signInBtn = new JButton("");
		signInBtn.setBackground(new Color(34, 77, 96));
		signInBtn.setIcon(new ImageIcon("C:\\images\\signin_fin_un.jpg"));
		signInBtn.setPressedIcon(new ImageIcon("C:\\images\\signin_fin_click.jpg"));
		signInBtn.setBounds(179, 520, 200, 62);
		signInBtn.setBorderPainted(false);

		signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원 가입 버튼 클릭 시 동작하는 코드
				String username = idField.getText();
				String name = nameField.getText();
				String password = new String(passwordField.getPassword());
				String phone = hpField.getText();
				// 프로필 사진은 나중에 처리할 수 있도록 코드를 추가하세요.

				// 여기에 회원 가입 처리 코드를 작성하세요
				try {
					// JDBC 연결
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root",
							"qwe123!@#");
					Statement stmt = conn.createStatement();
					
					File imgfile = new File(imagePath);                                     	
			             
	                FileInputStream fis = new FileInputStream(imgfile);	

					// 사용자 정보를 user 테이블에 삽입하는 SQL 쿼리
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
					pstmt.close();
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		panel.add(signInBtn);
		frame.setVisible(true);
		}
}
