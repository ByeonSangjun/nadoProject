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
	
	//조직도 회원 리스트 조회용
    public ArrayList<Employee> selectList(Object comp) {
       Connection conn = getConnection(comp);
       ArrayList<Employee> list = eDao.selectList(conn);
       close(conn);
       return list;
    }   

    //조직도 부서 리스트 조회용
    public ArrayList<Dept> dselectList(Object comp) {
       Connection conn = getConnection(comp);
       ArrayList<Dept> dlist = eDao.dselectList(conn);
       close(conn);
       return dlist;
    }
    
    //조직도 리스트 상세보기 조회용
    public Employee empListDetail(Object comp, String empid) {
       Connection conn = getConnection(comp);
       Employee employee = eDao.empListDetail(conn, empid);
       close(conn);
       return employee;
    }
		
		
		public Employee selectOne(Object comp, String empId) {
			Connection conn = getConnection(comp);
			Employee employee = eDao.selectOne(conn, empId);
			close(conn);
			return employee;
		}
		
		public Employee employeeInfo(Object comp, String empId) {
			Connection conn = getConnection(comp);
			Employee emp = eDao.employeeInfo(conn, empId);
			close(conn);
			return emp;
		}
		public ArrayList<Employee> searchDeptId(Object comp) {
			Connection conn = getConnection(comp);
			ArrayList<Employee> list = eDao.searchDeptId(conn);
			close(conn);
			return list;
		}
		public ArrayList<Employee> searchJobId(Object comp) {
			Connection conn = getConnection(comp);
			ArrayList<Employee> list = eDao.searchJobId(conn);
			close(conn);
			return list;
		}
		public ArrayList<Employee> searchPaystep(Object comp) {
			Connection conn = getConnection(comp);
			ArrayList<Employee> list = eDao.searchPaystep(conn);
			close(conn);
			return list;
		}
		
		public int emailCheck(Object comp, String email) {
			Connection conn = getConnection(comp);
			int result = eDao.emailCheck(conn, email);
			close(conn);			
			return result;
		}
		
		public int phoneCheck(Object comp, String phone) {
			Connection conn = getConnection(comp);
			int result = eDao.emailCheck(conn, phone);
			close(conn);			
			return result;
		}
		public String searchEmpId(Object comp, String userId) {
			Connection conn = getConnection(comp);
			String empId = eDao.searchEmpId(conn, userId);
			close(conn);
			return empId;
		}
		public int updateState(Object comp, String empId) {
			Connection conn = getConnection(comp);
			int result = eDao.updateState(conn, empId);
			if(result >0)
				commit(conn);
			else
				rollback(conn);
			return result;
		}
		
	// 형규 email address list 조회용 서비스
	public ArrayList<Employee> selectAddress(Object comp) {
		Connection conn = getConnection(comp);
		ArrayList<Employee> list = eDao.selectAddress(conn);
		close(conn);
		return list;
	}

}
