package cloud.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cloud.model.vo.Cloud;

public class CloudDao {
	
	public CloudDao() {}

	
	//클라우드 로그인
	public Cloud loginCloud(Connection conn, String clId) {
		Cloud cloud = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from cloud where cl_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, clId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {			

				cloud = new Cloud();

				cloud.setClNo(rset.getInt("cl_no"));
				cloud.setClName(rset.getString("cl_name"));
				cloud.setClId(rset.getString("cl_id"));
				cloud.setClPwd(rset.getString("cl_pwd"));
				cloud.setClPhone(rset.getString("cl_phone"));
				cloud.setClEmail(rset.getString("cl_email"));
				cloud.setClAddress(rset.getString("cl_address"));
				cloud.setClDate(rset.getDate("cl_date"));		
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return cloud;
	}


	public int enrollCloud(Connection conn, Cloud cloud) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into cloud values(cl_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cloud.getClName());
			pstmt.setString(2, cloud.getClId());
			pstmt.setString(3, cloud.getClPwd());
			pstmt.setString(4, cloud.getClPhone());
			pstmt.setString(5, cloud.getClEmail());
			pstmt.setString(6, cloud.getClAddress());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;

	}

	//비밀번호 확인
	public Cloud checkPwd(Connection conn, String clId, String clUserPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cloud cloud = null;
		String query = "select * from cloud where cl_id = ? and cl_pwd = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, clId);
			pstmt.setString(2, clUserPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cloud = new Cloud();

				cloud.setClNo(rset.getInt("cl_no"));
				cloud.setClName(rset.getString("cl_name"));
				cloud.setClId(rset.getString("cl_id"));
				cloud.setClPwd(rset.getString("cl_pwd"));
				cloud.setClPhone(rset.getString("cl_phone"));
				cloud.setClEmail(rset.getString("cl_email"));
				cloud.setClAddress(rset.getString("cl_address"));
				cloud.setClDate(rset.getDate("cl_date"));		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return cloud;
	}


	public int updateCloud(Connection conn, Cloud cloud) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update cloud set cl_name = ?, cl_id = ?, cl_pwd = ?, cl_phone = ?, cl_email=?, cl_address = ? where cl_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cloud.getClName());
			pstmt.setString(2, cloud.getClId());
			pstmt.setString(3, cloud.getClPwd());
			pstmt.setString(4, cloud.getClPhone());
			pstmt.setString(5, cloud.getClEmail());
			pstmt.setString(6, cloud.getClAddress());
			pstmt.setInt(7, cloud.getClNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int idCheck(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select cl_id from cloud where cl_id= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result += 1;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}


	public int emailCheck(Connection conn, String email) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select cl_email from cloud where cl_email= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result += 1;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

}
