package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/new_schema", "root", "123456");
			
		} catch (Exception e) {
			MainController.callAlert("연결 실패 : DB 연결에 실패하였습니다.\n점검바람 : " + e.getMessage());
			e.printStackTrace();             
			return null;
		}
		return con;
	}
}
