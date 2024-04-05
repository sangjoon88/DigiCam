package admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteUserInfoScreen extends JFrame {

    private JLabel lblImage;
    private JButton printButton;
    private JTextField idField;
    private JLabel idLabel;
    private JTable table;
    private JLabel allUsersLabel;

    public DeleteUserInfoScreen() {
        setTitle("회원 정보 삭제");
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

        idLabel = new JLabel("삭제할 ID를 입력하세요:");
        idLabel.setFont(new Font("나눔스퀘어라운드", Font.BOLD, 20)); // 글꼴 크기 조정
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(idLabel, gbc);

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30)); // id 텍스트 필드의 크기 설정
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

        printButton = new JButton("회원 정보 삭제");
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
                // 아이디 입력 받기
                String username = idField.getText();
                // 회원 삭제 메소드 호출
                deleteUserInfoFromDatabase(username);
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

    // 회원 삭제 메소드
    void deleteUserInfoFromDatabase(String userid) {
        try {
            // JDBC 연결
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collytalk", "root", "qwe123!@#");
            String sql = "DELETE FROM user WHERE user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid); // 사용자 이름으로 바인딩
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "회원 정보가 삭제되었습니다.");
                displayAllUserNames(); // 삭제 후 목록을 다시 표시
            } else {
                JOptionPane.showMessageDialog(null, "해당하는 회원 정보가 없습니다.");
            }

            // 자원 해제
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터베이스 오류: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DeleteUserInfoScreen();
    }
}