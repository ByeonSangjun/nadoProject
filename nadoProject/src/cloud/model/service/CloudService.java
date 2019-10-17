package cloud.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import cloud.model.dao.CloudDao;
import cloud.model.vo.Cloud;


public class CloudService {
	
	private CloudDao clDao = new CloudDao();
	
	public CloudService() {}

	public Cloud loginCloud(Object comp, String clId) {
		Connection conn = getConnection(comp);
		Cloud cloud = clDao.loginCloud(conn, clId);
		close(conn);
		return cloud;
	}

}
