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

}
