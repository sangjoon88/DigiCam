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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateKickOutWordScreen extends JFrame {

	private JLabel lblImage;
	private JLabel KickOutLabel;
	private JTextField KickOutField;
	private JLabel KickOutWordLabel;
	private JButton createButton;


	
	public void createScreen() {
		setTitle("강퇴 용어 등록");
		setSize(500, 700);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		lblImage = new JLabel(new ImageIcon("images/kollytalk.jpg"));
		lblImage.setHorizontalAlignment(JLabel.CENTER);

		// 레이아웃 설정
		setLayout(new BorderLayout());
		add(lblImage, BorderLayout.NORTH); // 이미지 레이블을 상단에 추가

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		KickOutLabel = new JLabel("지정할 강퇴 용어를 입력하세요:");
		KickOutLabel.setFont(new Font("나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(KickOutLabel, gbc);

		KickOutField = new JTextField();
		KickOutField.setPreferredSize(new Dimension(200, 30)); // id 텍스트 필드의 크기 설정
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(KickOutField, gbc);
		
		createButton = new JButton("등록");
		createButton.setFont(new Font("Pretendard", Font.BOLD, 16)); // 글꼴 크기 조정
		createButton.setPreferredSize(new Dimension(50, 40)); // 조회 버튼의 크기 설정
        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        
        // 버튼에 ActionListener 추가
        createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createKickOutWord();
			}
        });
        
        panel.add(createButton, gbc);

		// 아래에 현재 모든 회원 목록의 이름을 출력하는 코드
		KickOutWordLabel = new JLabel();
		KickOutWordLabel.setVerticalAlignment(JLabel.TOP); // 텍스트 상단 정렬
		KickOutWordLabel.setFont(new Font("나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
		gbc.gridx = 0;
		gbc.gridy = 3;
		//gbc.gridwidth = 2;
		panel.add(KickOutWordLabel, gbc);

		add(panel);
		setVisible(true);
	}

	public void getKickOutWordList() {

		// JDBC 연결
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
			Statement stmt = conn.createStatement();
			// 모든 회원의 이름을 가져오는 쿼리
			String sql = "SELECT kickout_word FROM kickout";
			ResultSet rs = stmt.executeQuery(sql);

			// 이름을 담을 StringBuilder 객체 생성
            StringBuilder allKickOutWords = new StringBuilder("<html>강퇴 용어 현황: <br>");

            // 모든 회원의 이름을 StringBuilder에 추가
            while (rs.next()) {
                String kickout_word = rs.getString("kickout_word");
                allKickOutWords.append(kickout_word).append("<br>");
            }

            allKickOutWords.append("</html>");
            
            KickOutWordLabel.setText(allKickOutWords.toString());
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	void createKickOutWord(){
		String word = KickOutField.getText();
		System.out.println(word);
		
        try {
            // JDBC 연결
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
            Statement stmt = conn.createStatement();

            // 사용자 정보를 user 테이블에 삽입하는 SQL 쿼리
            String sql = "INSERT INTO kickout (kickout_word) VALUES ('" + word + "');";
            
            // SQL 쿼리 실행
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "등록이 완료되었습니다.");
                
            } else {
                JOptionPane.showMessageDialog(null, "등록이 실패했습니다.");
            }
            getKickOutWordList();
            // 자원 해제
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
        }
		
		
	}

	public static void main(String[] args) {
		CreateKickOutWordScreen createKickOutWordScreen = new CreateKickOutWordScreen();
		createKickOutWordScreen.createScreen();
		createKickOutWordScreen.createKickOutWord();
		createKickOutWordScreen.getKickOutWordList();
	}

}
