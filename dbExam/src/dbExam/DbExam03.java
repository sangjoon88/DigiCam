package dbExam;  // DB 연결만 한 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbExam03 {

	Connection conn; // DB 정보에 대해서 담는애
	PreparedStatement pstmt;  

	public DbExam03() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam03(String url, String user, String pw) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		DbExam03 exam = new DbExam03();
		System.out.println("success");
		
		
		//exam.dbInput("angel");
		
		String str[] = {"angle", "micle", "dong"};
		for (int i = 0; i < str.length; i++	) {
			exam.dbInput(i, str[i]);
		}
		
	}

	private void dbInput(int a, String name) {
		
		try {
			String sql = "insert into student2 values(?, ?)";    // 아랫줄이랑 세트로 움직인다
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a);  						 // 1번쨰 물음표
			pstmt.setString(2, name); 				// 2번째 물음표
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}