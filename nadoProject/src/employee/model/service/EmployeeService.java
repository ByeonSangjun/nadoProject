package employee.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import employee.model.dao.EmployeeDao;
import employee.model.vo.Company;
import employee.model.vo.Employee;

public class EmployeeService {

	private EmployeeDao eDao = new EmployeeDao();

	//아이디 받아서 사원 정보 꺼내기(로그인)
	public Employee loginEmployee(Object comp , String userId) {
		Connection conn = getConnection(comp);
		Employee emp = eDao.loginEmployee(conn, userId);
		close(conn);
		return emp;
	}
	//아이디 받아서 같은 아이디 존재하는지 확인
	public int idCheck(Object comp, String userId) {
		Connection conn = getConnection(comp);
		int result =eDao.idCheck(conn, userId);
		close(conn);
		return result;
	}

	public int enrollEmployee(Object comp, Employee emp) {
		Connection conn = getConnection(comp);
		int result = eDao.enrollEmployee(conn, emp);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Employee> incompleteEmployee(Object comp) {
		Connection conn = getConnection(comp);
		ArrayList<Employee> list = eDao.incompleteEmployee(conn); 
		return list;
	}

	public int approEmployee(Object comp, Employee emp) {
		Connection conn = getConnection(comp);
		int result = eDao.approEmployee(conn, emp);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;
	}

	

}
