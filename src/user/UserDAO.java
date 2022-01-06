package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn; //DB에 접근하게 해주는 객체 생성
	
	//Connection 을 통해 DB에 접근하였으면 접근한 객체에게 Query작업을 할 수 있게 해주는 객체(Staement)에서 보안을 추가한 객체
	private PreparedStatement pstmt; 
	
	private ResultSet rs; //정보를 담을 수 있는 객체
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS"; //사용하고 있는 데이터 베이스의 주소
			String dbID ="root";
			String dbPassword = "Fpdlfmrjs2@";
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql에 접속하는 매개체
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM user WHERE userID = ? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {//인젝션 해킹등 보안을 적용한 기법? 에 ID값을 받은 후 사용
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else
					return 0; //로그인 실패 비밀번호 불일치
			}
			return -1; //아이디가 없음
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return -2; // DB오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
		
	}
	
}
