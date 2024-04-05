//회원목록 리스트가 많아지면 스크롤기능 구현필요 
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

import javax.swing.AbstractButton;
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

public class SearchUserInfoScreen extends JFrame {

    private JLabel lblImage;
	private AbstractButton printButton;
	private JTextField idField;
	private JLabel idLabel;
	private JTable table;
	private JLabel allUsersLabel;

	SearchUserInfoScreen(String userId) {
    }

    public SearchUserInfoScreen() {
        setTitle("회원 정보 조회");
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

        idLabel = new JLabel("조회할 ID를 입력하세요:");
        idLabel.setFont(new Font("나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(20, 50)); // id 텍스트 필드의 크기 설정
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idField, gbc);

        // 아래에 현재 모든 회원 목록의 이름을 출력하는 코드
        allUsersLabel = new JLabel();
        allUsersLabel.setVerticalAlignment(JLabel.TOP); // 텍스트 상단 정렬
        allUsersLabel.setFont(new Font("나눔스퀘어라운드", Font.BOLD, 16)); // 글꼴 크기 조정
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(allUsersLabel, gbc);

        // 현재 모든 회원 목록의 이름을 가져오는 메서드 호출
        displayAllUserNames();

        printButton = new JButton("조회");
        printButton.setFont(new Font("Pretendard", Font.BOLD, 16)); // 글꼴 크기 조정
        printButton.setPreferredSize(new Dimension(50, 40)); // 조회 버튼의 크기 설정
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
      

                // 회원목록 조회 메소드 호출
                getUserInfoFromDatabase(username);
            }
        });

        add(panel);
        setVisible(true);
    }

    // 현재 모든 회원 목록의 이름을 가져와 출력하는 메서드
    void displayAllUserNames() {
        try {
            // JDBC 연결
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
            Statement stmt = conn.createStatement();

            // 모든 회원의 이름을 가져오는 쿼리
            String sql = "SELECT user_id FROM user";
            ResultSet rs = stmt.executeQuery(sql);

            // 이름을 담을 StringBuilder 객체 생성
            StringBuilder allUserNames = new StringBuilder("<html>현재 회원 현황: <br>");

            // 모든 회원의 이름을 StringBuilder에 추가
            while (rs.next()) {
                String username = rs.getString("user_id");
                allUserNames.append(username).append("<br>");
            }

            allUserNames.append("</html>");

            // JLabel에 현재 모든 회원 목록의 이름을 설정
            allUsersLabel.setText(allUserNames.toString());

            // 자원 해제
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
        }
    }

    // 회원 조회 메소드
    void getUserInfoFromDatabase(String userid) {
        // 데이터베이스에서 회원목록가져오기
        try {
            // JDBC 연결
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
            Statement stmt = conn.createStatement();

            // 입력된 아이디와 날짜를 이용하여 데이터베이스에서 정보를 조회
            String sql = "SELECT * FROM user WHERE user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid); // 사용자 이름으로 바인딩
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
        new SearchUserInfoScreen();
    }


    
}