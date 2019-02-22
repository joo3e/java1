package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Product;
import Model.User;
import Model.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DATA {
	
	public static ArrayList<User> dbArrList1 = new ArrayList<>();
	public static ArrayList<User> dbArrList4 = new ArrayList<>();
	public static ArrayList<Product> dbArrList2 = new ArrayList<>();
	public static ArrayList<String> dbArrList3 = new ArrayList<>();
	public static ArrayList<UserInfo> dbArrList5 = new ArrayList<>();
	public static int c;
	
	public static int insertUserData(User user) {
		StringBuffer insertUser = new StringBuffer();
		insertUser.append("insert into usertbl ");
		insertUser.append("(userID,name,birthYear,addr,mobile1,mobile2,height,mdate) ");
		insertUser.append("values ");
		insertUser.append("(?,?,?,?,?,?,?,?) ");
		Connection con = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUser.toString());
			psmt.setString(1, user.getUserid());
			psmt.setString(2, user.getName());
			psmt.setString(3, user.getBirthYear());
			psmt.setString(4, user.getAddr());
			psmt.setString(5, user.getMobile1());
			psmt.setString(6, user.getMobile2());
			psmt.setString(7, user.getHeight());
			psmt.setString(8, user.getMdate());
		
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패 점검바람");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			//자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}

		}

		return count;
	}

	public static int insertbuyTblData(Product product) {
		StringBuffer insertUser = new StringBuffer();
		insertUser.append("insert into buyTbl ");
		insertUser.append("(num,userID,prodName,groupName,price,amount) ");
		insertUser.append("values ");
		insertUser.append("(?,?,?,?,?,?) ");
		Connection con = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUser.toString());
			psmt.setString(1, null);
			psmt.setString(2, product.getUserID());
			psmt.setString(3, product.getProdName());
			psmt.setString(4, product.getGroupName());
			psmt.setString(5, product.getPrice());
			psmt.setString(6, product.getAmount());
		
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("삽입 쿼리실패 : 삽입 쿼리문 실패 점검바람");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}
		}
		return count;
	}

	public static ArrayList<User> getuserTotalData() {
		String selectuser = "select * from usertbl";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				dbArrList1.add(user);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}

		}
		return dbArrList1;
	}

	public static ArrayList<Product> getproductTotalData() {
		String selectbuyTbl = "select * from buyTbl";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectbuyTbl);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				dbArrList2.add(product);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}

		}
		return dbArrList2;
	}
	
	public static int updateUserData(User user) {
		int count = 0;
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update usertbl set ");
		updateStudent.append("name=?,birthYear=?,addr=?,mobile1=?,mobile2=?,height=?,mdate=? where userID=? ");

		
		Connection con = null;
		
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());

		
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getBirthYear());
			psmt.setString(3, user.getAddr());
			psmt.setString(4, user.getMobile1());
			psmt.setString(5, user.getMobile2());
			psmt.setString(6, user.getHeight());
			psmt.setString(7, user.getMdate());
			psmt.setString(8, user.getUserid());
			
			
			count = psmt.executeUpdate();

			if (count == 0) {
				MainController.callAlert("UPDATE 쿼리문 실행 실패 : 쿼리문 실행하는 데에 문제가 발생했어요.");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("UPDATE 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
			
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : UPDATE psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return count;
	}
	public static int updatebuyData(Product product) {
		int count = 0;
		StringBuffer updateStudent = new StringBuffer();
		updateStudent.append("update buytbl set ");
		updateStudent.append("userID=?,prodName=?,groupName=?,price=?,amount=? where num=? ");
		
		
		Connection con = null;
		
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateStudent.toString());
			
			
			psmt.setString(1, product.getUserID());
			psmt.setString(2, product.getProdName());
			psmt.setString(3, product.getGroupName());
			psmt.setString(4, product.getPrice());
			psmt.setString(5, product.getAmount());
			psmt.setString(6, product.getNum().toString());
			
			
			
			count = psmt.executeUpdate();
			
			if (count == 0) {
				MainController.callAlert("UPDATE 쿼리문 실행 실패 : 쿼리문 실행하는 데에 문제가 발생했어요.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("UPDATE 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : UPDATE psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
				
			}
		}
		return count;
	}
	public static ArrayList<String> getuserid() {
		String selectuser = "select userID from usertbl ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				
				String string = rs.getString(1);
				dbArrList3.add(string);
				
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}

		}
		return dbArrList3;
	}
	public static int deleteuserData(String userid) {
		String deleteuser = "delete from usertbl where userID = ? ";

		// 3.2 DELETE DataBase Connection
		Connection con = null;
		// 3.3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		//3.4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		int count = 0;
		
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteuser);
			//첫번째 물음표 자리 -> no 매치 시켜주는 작업 
			psmt.setString(1, userid);
	
			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
			count = psmt.executeUpdate();

			if (count == 0) {
				MainController.callAlert("DELETE 쿼리문 실행 실패 : DELETE 쿼리문 실행하는 데에 문제가 발생했어요.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		return count;
	}
	public static int deletebuyData(String num) {
		String deleteuser = "delete from buyTbl where num = ? ";
		
		// 3.2 DELETE DataBase Connection
		Connection con = null;
		// 3.3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		//3.4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		int count = 0;
		
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteuser);
			//첫번째 물음표 자리 -> no 매치 시켜주는 작업 
			psmt.setString(1, num);
			
			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
			count = psmt.executeUpdate();
			
			if (count == 0) {
				MainController.callAlert("DELETE 쿼리문 실행 실패 : DELETE 쿼리문 실행하는 데에 문제가 발생했어요.");
				return count;
			}
			
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		return count;
	}
	public static ArrayList<User> getselecteddate(String a, String b) {
		
		 
		String getuser="select * from usertbl where mdate between ? and ? ";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		//2.4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(getuser);
			psmt.setString(1, a);
			psmt.setString(2, b);
	
			// 2.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();

			if (rs == null) {
				MainController.callAlert("select 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while(rs.next()) {
				User user =  new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				dbArrList4.add(user);
			}
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return dbArrList4;
	}
	public static  ArrayList<UserInfo> getstabledata(String a) {
		String selectuser = "SELECT name, concat(mobile1,mobile2), prodName, amount, price*amount FROM buytbl INNER JOIN usertbl ON buytbl.UserID = usertbl.UserID where buytbl.UserID = ? ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectuser);
			psmt.setString(1, a);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				UserInfo userinfo = new UserInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				dbArrList5.add(userinfo);
			}

		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}

		}
		return dbArrList5;
	}
	public static int getselectedheight(int a, int b) {
		
		 
		String getuser="select count(*) from usertbl where birthYear between ? and ? and height between ? and ? ";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		//2.4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(getuser);
			psmt.setString(1, String.valueOf(1950+a*10));
			psmt.setString(2, String.valueOf(1959+a*10));
			psmt.setString(3, String.valueOf(160+b*10));
			psmt.setString(4, String.valueOf(169+b*10));
			
	
			// 2.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();

			if (rs == null) {
				MainController.callAlert("select 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return 0;
			}
			while(rs.next()) {
			 c = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			MainController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				MainController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return c;
	}
}

