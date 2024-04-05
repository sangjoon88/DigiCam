package dbExam;  // DB 연결만 한 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbExam02 {

	Connection conn; // DB 정보에 대해서 담는애
	PreparedStatement pstmt;  

	public DbExam02() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam02(String url, String user, String pw) {
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
		// TODO Auto-generated method stub
		DbExam02 exam = new DbExam02();
		System.out.println("success");
		
		exam.dbInput();
	}

	private void dbInput() {                                                       
		
		try {
			String sql = "insert into student2 values(7, 'superman')";   // 입력하는 쿼리문
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
