package employee.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import employee.model.dao.EmployeeDao;
import employee.model.vo.Dept;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	//우수
	
		public ArrayList<Employee> selectList(Object comp) {
			Connection conn = getConnection(comp);
			ArrayList<Employee> list = eDao.selectList(conn);
			close(conn);
			return list;
		}
		
		
		public Employee selectOne(Object comp, String empId) {
			Connection conn = getConnection(comp);
			Employee employee = eDao.selectOne(conn, empId);
			close(conn);
			return employee;
		}
		
		//그룹웨어 관리자용 조직도 리스트
		public ArrayList<Dept> selectgwList(Object comp) {
			Connection conn = getConnection(comp);
			ArrayList<Dept> dlist = eDao.selectgwList(conn);
			close(conn);
			return dlist;
		}
		

	

}
