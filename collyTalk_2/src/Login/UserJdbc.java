package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserJdbc {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public UserJdbc() {
		this("jdbc:mysql://localhost:3306/kollytalk?serverTimezone=UTC", "root", "qwe123!@#"); // 하단의 생성자 호출
	}

	public UserJdbc(String url, String user, String pw) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 로드
			conn = DriverManager.getConnection(url, user, pw); // 드라이버 매니저를 통해 db 접속
		} catch (ClassNotFoundException e) { // 드라이버 미확인 시 에러 발생
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<User> reload() {
		try {
			ArrayList<User> list = new ArrayList<User>();
			String sql = "select * from user";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString(1));
				user.setPw(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNickName(rs.getString(4));
				user.setHp(rs.getString(5));				
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void dbInput(String id, String pwd, String name, String nickName, String hp) {
		try {
			String sql = "insert into user values(?,?,?,?,?,null)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, nickName);
			pstmt.setString(5, hp);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void delete(String str) {
		try {
			String sql = "delete from user where user_name=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void update(String str) {
		try {
			String sql = "update user set user_name=? where id=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void result() throws SQLException {
		String sql = "select * from user";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			int deptNo = rs.getInt(1);
			String deptName = rs.getString(2);

			System.out.println(deptName + " " + deptNo);
		}

	}

	private void dbInput3(String[] str) {
		try {
			String sql = "insert into user values(?,?)";
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < str.length; i++) {
				pstmt.setInt(1, i);
				pstmt.setString(2, str[i]);
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
