package dbExam;  // DB 연결만 한 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbExam01 {       // 요 클래스는 가지고 계세요 디비 연결할 때 항상 있어야 함.

	Connection conn; // DB 정보에 대해서 담는애  

	public DbExam01() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam01(String url, String user, String pw) {
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
		DbExam01 exam = new DbExam01();
		System.out.println("success");
	}

	
	
}
