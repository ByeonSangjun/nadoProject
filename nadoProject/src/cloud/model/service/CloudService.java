package cloud.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import cloud.model.dao.CloudDao;
import cloud.model.vo.Cloud;
import employee.model.vo.Company;


public class CloudService {
	
	private CloudDao clDao = new CloudDao();
	
	public CloudService() {}

	public Cloud loginCloud(Object comp, String clId) {
		Connection conn = getConnection(comp);
		Cloud cloud = clDao.loginCloud(conn, clId);
		close(conn);
		return cloud;
	}

	public int enrollCloud(Company comp, Cloud cloud) {
		Connection conn = getConnection(comp);
		int result = clDao.enrollCloud(conn, cloud);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public Cloud checkPwd(Company comp, String clId, String clUserPwd) {
		Connection conn = getConnection(comp);
		Cloud cloud = clDao.checkPwd(conn, clId, clUserPwd);
		close(conn);
		return cloud;
	}

	public int updateCloud(Company comp, Cloud cloud) {
		Connection conn = getConnection(comp);
		int result = clDao.updateCloud(conn, cloud);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public int idCheck(Company comp, String userId) {
		Connection conn = getConnection(comp);
		int result = clDao.idCheck(conn, userId);
		close(conn);			
		return result;
	}

	public int emailCheck(Company comp, String email) {
		Connection conn = getConnection(comp);
		int result = clDao.emailCheck(conn, email);
		close(conn);			
		return result;
	}

}
