package dbExam;  // DB 연결만 한 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbExam4 {

	Connection conn; // DB 정보에 대해서 담는애
	PreparedStatement pstmt;  

	public DbExam4() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam4(String url, String user, String pw) {
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

		DbExam4 exam = new DbExam4();
		System.out.println("success");
		exam.dbInput("Superman");
		
	}

	private void dbInput(String str) {
		
		try {
			String sql = "UPDATE STUDENT2 SET USERNAME=? WHERE ID =1"; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, str);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}