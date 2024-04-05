package dbExam; //  arraylist 만들어서 출력한 것, 이너클래스로 만들고 실행 함..ㅎ

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbExam6 {
	Connection conn;
	PreparedStatement pstmt;

	public DbExam6() {
		this("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC", "root", "qwe123!@#");
	}

	public DbExam6(String url, String user, String pw) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class EmpVO {
		private int empNo;
		private String eName;
		private int sal;

		public int getEmpNo() {
			return empNo;
		}

		public void setEmpNo(int empNo) {
			this.empNo = empNo;
		}

		public String geteName() {
			return eName;
		}

		public void seteName(String eName) {
			this.eName = eName;
		}

		public int getSal() {
			return sal;
		}

		public void setSal(int sal) {
			this.sal = sal;
		}

	}

	public void result() {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();

		try {
			String sql = "SELECT * FROM EMP ORDER BY SAL DESC";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpVO empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("EMPNO"));
				empVO.seteName(rs.getString("ENAME"));
				empVO.setSal(rs.getInt("SAL"));
				list.add(empVO);

//				System.out.print(rs.getInt("EMPNO") + " ");
//				System.out.print(rs.getString("ENAME") + " ");
//				System.out.println(rs.getInt("SAL"));
			}

			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getEmpNo() + " ");
				System.out.print(list.get(i).geteName() + " ");
				System.out.println(list.get(i).getSal());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DbExam6 exam = new DbExam6();
		System.out.println("success");
		exam.result();
	}
}