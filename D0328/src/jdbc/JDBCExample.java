package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) {
		// 데이터베이스 연결 정보
		String url = "jdbc:mysql://localhost:3306/mydatabase"; // MySQL의 경우
		String user = "username";
		String password = "password";

		// JDBC 연결
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			System.out.println("Database 연결 성공!");

			// SQL 쿼리 실행
			try (Statement statement = connection.createStatement()) {
				String sql = "SELECT * FROM my_table";
				try (ResultSet resultSet = statement.executeQuery(sql)) {
					// 결과 처리
					while (resultSet.next()) {
						// 결과 가져오기
						int id = resultSet.getInt("id");
						String name = resultSet.getString("name");
						// 결과 출력 또는 처리
						System.out.println("ID: " + id + ", Name: " + name);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
