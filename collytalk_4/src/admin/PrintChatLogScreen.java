//대화로그 많을 시 스크롤 추가?
//날짜별 따로, 이름별 따로로 구현해야할까..? 현재는 두가지 모두 충족되어야 출력.
package admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class PrintChatLogScreen extends JFrame {
	private JLabel idLabel, chatDateLabel;
	private JTextField idField;
	private JButton printButton, registerButton, adminLoginButton, exitButton;
	private JLabel lblImage;
	private Statement stmt;
	private PreparedStatement pstmt;
	private JTable table;

	PrintChatLogScreen(String userId) {
	}

	public PrintChatLogScreen() {
		setTitle("채팅 로그 조회");
		setSize(500, 700);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		lblImage = new JLabel(new ImageIcon("images/kollytalk.jpg"));
		lblImage.setHorizontalAlignment(JLabel.CENTER);

		// 레이아웃 설정
		setLayout(new BorderLayout());
		add(lblImage, BorderLayout.NORTH); // 이미지 레이블을 상단에 추가

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		idLabel = new JLabel("조회할 이름을 입력하세요:");
		idLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);

		idField = new JTextField();
		idField.setPreferredSize(new Dimension(200, 30)); // id 텍스트 필드의 크기 설정
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(idField, gbc);

		chatDateLabel = new JLabel("조회할 날짜를 입력하세요:");
		chatDateLabel.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(chatDateLabel, gbc);

		JTextField chatDateField = new JTextField();
		chatDateField.setPreferredSize(new Dimension(200, 30)); // 날짜 텍스트 필드의 크기 설정
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(chatDateField, gbc);

		printButton = new JButton("조회");
		printButton.setFont(new Font("@나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
		printButton.setPreferredSize(new Dimension(150, 40)); // 조회 버튼의 크기 설정
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panel.add(printButton, gbc);

		table = new JTable(new DefaultTableModel()); // 테이블 모델 초기화
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH; // 스크롤이 필요한 경우 모든 공간을 차지하도록 함
		panel.add(scrollPane, gbc);

		// 버튼에 ActionListener 추가
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 아이디와 날짜 입력 받기
				String username = idField.getText();
				String chatdate = chatDateField.getText();

				// 채팅 로그 조회 메소드 호출
				getMessageFromDatabase(username, chatdate);
			}
		});

		add(panel);
		setVisible(true);
	}

	// 채팅 로그 조회 메소드
	void getMessageFromDatabase(String userid, String chatdate) {
		// 데이터베이스에서 채팅 로그 가져오기
		try {
			// JDBC 연결
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
			Statement stmt = conn.createStatement();

			// 입력된 아이디와 날짜를 이용하여 데이터베이스에서 정보를 조회
			String sql = "SELECT * FROM chat_log WHERE user_id=? AND STR_TO_DATE(chat_date, '%Y-%m-%d') = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid); // 사용자 이름으로 바인딩
			pstmt.setString(2, chatdate); // 날짜
			ResultSet rs = pstmt.executeQuery();

			// 테이블 모델 생성
			DefaultTableModel tableModel = new DefaultTableModel();

			// 쿼리 결과를 테이블 모델로 변환하여 테이블에 설정
			table.setModel(buildTableModel(rs));

			// 자원 해제
			rs.close();
			pstmt.close();
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
		}
	}

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		// 쿼리 결과의 컬럼 수 계산
		int columnCount = metaData.getColumnCount();

		// 테이블 모델 생성
		DefaultTableModel tableModel = new DefaultTableModel();

		// 컬럼 이름을 컬럼 모델에 추가
		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			tableModel.addColumn(metaData.getColumnLabel(columnIndex));
		}

		// 테이블에 데이터 추가
		while (rs.next()) {
			ArrayList<Object> row = new ArrayList<>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				row.add(rs.getObject(columnIndex));
			}
			tableModel.addRow(row.toArray());
		}

		return tableModel;
	}

	public static void main(String[] args) {
		new PrintChatLogScreen();
	}

}