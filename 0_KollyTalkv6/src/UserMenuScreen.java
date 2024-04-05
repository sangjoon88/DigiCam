import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.EditProfileScreen;
import java.awt.Font;
import javax.swing.JTextArea;

public class UserMenuScreen extends JFrame {
	private String userId;
	private JFrame frame;
	PreparedStatement pstmt;

	/**
	 * Create the application.
	 */
	public UserMenuScreen(String userId) {
		this.userId = userId;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 700);
		frame.setTitle("KollyTalk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(34, 77, 96));
		panel.setBounds(0, 0, 484, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel mainLogo = new JLabel(new ImageIcon("C:\\images\\kollytalk_logo.jpg"));
		mainLogo.setBounds(0, 20, 472, 159);
		panel.add(mainLogo);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(34, 77, 96));
		panel_1.setBounds(0, 466, 484, 95);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton infoBtn = new JButton("");
		infoBtn.setIcon(new ImageIcon("C:\\images\\infochange_un.jpg"));
		infoBtn.setPressedIcon(new ImageIcon("C:\\images\\infochange_click.jpg"));
		infoBtn.setBounds(247, 10, 213, 69);
		infoBtn.setBorderPainted(false);
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 내 정보 변경 버튼 눌렀을 때의 동작
				new EditProfileScreen(userId);

				// 여기에 내 정보 변경 화면으로 이동하는 코드를 작성하세요
			}
		});
		panel_1.add(infoBtn);

		JButton chatBtn = new JButton("");
		chatBtn.setIcon(new ImageIcon("C:\\images\\chatenter_un.jpg"));
		chatBtn.setPressedIcon(new ImageIcon("C:\\images\\chatenter_cilck.jpg"));
		chatBtn.setBackground(new Color(34, 77, 96));
		chatBtn.setBorderPainted(false);
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 채팅방 입장 버튼 눌렀을 때의 동작
				ChatClient chatClient = new ChatClient(userId);
				chatClient.setVisible(true); // ChatClient 객체 표시
			}
		});
		chatBtn.setBounds(12, 10, 213, 69);
		panel_1.add(chatBtn);
		
		Image img = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");

			// 입력된 아이디와 비밀번호를 이용하여 데이터베이스에서 정보를 조회
			String query = ("SELECT user_pic FROM user WHERE user_id=\""+userId+"\";");

			pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery(); // rs 저장해서 뒤에 넥스트로 계속 출력

			while (rs.next()) {
				Blob getimg = rs.getBlob(1);
				byte[] imgbyte = getimg.getBytes(1, (int) getimg.length());
				try {
					img = ImageIO.read(new ByteArrayInputStream(imgbyte));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(img));
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setBounds(24, 244, 199, 206);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("My Profile");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(64, 188, 139, 61);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("상태메세지");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Cafe24 Ssurround Bold", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(303, 188, 130, 60);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(144, 12, 0, 0);
		
		String pmsg ="";
		
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");

			// 입력된 아이디와 비밀번호를 이용하여 데이터베이스에서 정보를 조회
			String query = ("SELECT p_message FROM pmsg_log WHERE user_id=\""+userId+"\" order by notice_date desc limit 1;");

			pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery(); // rs 저장해서 뒤에 넥스트로 계속 출력

			while (rs.next()) {
				pmsg = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(245, 244, 227, 206);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel(pmsg);
		lblNewLabel_4.setBounds(12, 5, 203, 191);
		panel_2.add(lblNewLabel_4);
		panel_2.add(lblNewLabel_3);
		panel.add(panel_2);
		
		frame.setVisible(true);
		
	}
}
