package employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import employee.model.vo.Employee;

public class EmployeeDao {

	// 아이디 받아서 사원한명 정보 꺼내기 (로그인)
	public Employee loginEmployee(Connection conn, String userId) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from employee where user_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {			

				emp = new Employee();

				emp.setEmpId(rset.getString("emp_id"));
				emp.setEmail(rset.getString("email"));
				emp.setMyProfile(rset.getString("my_profile"));
				emp.setUserId(userId);
				emp.setEmpName(rset.getString("emp_name"));
				emp.setUserPwd(rset.getString("user_pwd"));
				emp.setEmpNo(rset.getString("emp_no"));
				emp.setPhone(rset.getString("phone"));
				emp.setAddress(rset.getString("address"));
				emp.setDeptId(rset.getString("dept_id"));
				emp.setJobId(rset.getString("job_id"));
				emp.setPaystep(rset.getString("paystep"));
				emp.setEmpPhone(rset.getString("emp_phone"));
				emp.setSalary(rset.getInt("salary"));
				emp.setBonus(rset.getDouble("bonus"));
				emp.setMarriage(rset.getString("marriage"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setIdLevel(rset.getString("id_level"));
				emp.setSign(rset.getString("sign"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return emp;
	}

	public int enrollEmployee(Connection conn, Employee emp) {
		PreparedStatement pstmt = null;
		int result =0;
		int random = (int)(Math.random() * 10000)+1;
		String empId = "a" + random;
		
		String query = "insert into employee values(?, ?, null, ?, ?, ?, ?, ?, ?, null, null, null, null, null, null, ?, sysdate, null, null)";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			pstmt.setString(2, emp.getEmail());
			pstmt.setString(3, emp.getUserId());
			pstmt.setString(4, emp.getEmpName());
			pstmt.setString(5, emp.getUserPwd());
			pstmt.setString(6, emp.getEmpNo());
			pstmt.setString(7, emp.getPhone());
			pstmt.setString(8, emp.getAddress());
			pstmt.setString(9, emp.getMarriage());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Employee> incompleteEmployee(Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query ="select * from employee where id_level is null";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Employee emp = new Employee(rset.getString("email"), rset.getString("user_id"),
						rset.getString("emp_name"), rset.getString("user_pwd"), rset.getString("emp_no"),
						rset.getString("phone"), rset.getString("address"), rset.getString("marriage"));
				emp.setHireDate(rset.getDate("hire_date"));
				emp.setEmpId(rset.getString("emp_id"));

				list.add(emp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	}

	public int approEmployee(Connection conn, Employee emp) {
		PreparedStatement pstmt = null;
		int result= 0;
		System.out.println(emp);
		
		String query = "update employee set emp_id = ?, id_level = ?, dept_id = ?, job_id = ?, paystep = ?, hire_date = sysdate where user_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getIdLevel());
			pstmt.setString(3, emp.getDeptId());
			pstmt.setString(4, emp.getJobId());
			pstmt.setString(5, emp.getPaystep());
			pstmt.setString(6, emp.getUserId());
			
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
		
		String query = "select user_id from employee where user_id= ?";
		
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

}
