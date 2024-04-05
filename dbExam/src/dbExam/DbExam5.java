package dbExam;  // DB 연결만 한 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbExam5 {

	Connection conn; // DB 정보에 대해서 담는애
	PreparedStatement pstmt;  

	public DbExam5() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam5(String url, String user, String pw) {
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

		DbExam5 exam = new DbExam5();
		System.out.println("success");
		exam.result();
		exam.result2(1);
		
	}

	private void result() {
		
		try {
			String sql = "SELECT * FROM STUDENT2"; 
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();    // rs 저장해서 뒤에 넥스트로 계속 출력 
			
			while (rs.next())	{
				System.out.print(rs.getInt(1) + " ");
				System.out.println(rs.getString(2));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
private void result2(int a) {
		
		try {
			String sql = "SELECT * FROM STUDENT2 WHERE ID = ?"; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, a);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())	{
				System.out.print(rs.getInt(1) + " ");
				System.out.println(rs.getString(2));
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}