package admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class AdminNoticeScreen extends JFrame {
	String admin_id = "kolly";
	String notice;

	public AdminNoticeScreen() {

		setTitle("KOLLYTALK");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("공지사항 입력");
		panel.add(lblNewLabel);

		JTextArea textArea = new JTextArea("공지사항을 이 곳에 작성해주세요.");
		getContentPane().add(textArea, BorderLayout.CENTER);

		JButton txtBtn = new JButton("입력완료");
		getContentPane().add(txtBtn, BorderLayout.SOUTH);

		setVisible(true);

		txtBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				notice = textArea.getText();

				try {
					// JDBC 드라이버 로드
					Class.forName("com.mysql.cj.jdbc.Driver");

					// DB 연결
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/collytalk?serverTimezone=UTC", "root", "qwe123!@#");

					// 입력된 아이디와 날짜를 이용하여 데이터베이스에서 정보를 조회
					String sql = "INSERT INTO notice_log (admin_id, notice_content) values(?,?);";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, admin_id); // 사용자 이름으로 바인딩
					pstmt.setString(2, notice); // 날짜

					// SQL 명령 실행
					pstmt.executeUpdate();
					pstmt.close();
					conn.close();
					dispose();
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
