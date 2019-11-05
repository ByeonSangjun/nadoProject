package employee.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import employee.model.vo.Dept;
import employee.model.vo.Employee;

public class EmployeeDao {

	// 아이디 받아서 사원한명 정보 꺼내기 (로그인)
	public Employee loginEmployee(Connection conn, String userId) {
		Employee emp = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from employee a left join job b on a.job_id = b.job_id left join dept c on a.dept_id = c.dept_id where user_id = ?";

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
				emp.setDeptName(rset.getString("dept_name"));
				emp.setJobName(rset.getString("job_name"));
				emp.setOriginalSign(rset.getString("original_sign"));
				emp.setReNameSign(rset.getString("rename_sign"));
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
		
		String query = "insert into employee values(?, ?, null, ?, ?, ?, ?, ?, ?, null, null, null, null, null, null, ?, sysdate, null, null, null)";
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	   // 우수
	   
    //조직도 회원 리스트 조회용
    public ArrayList<Employee> selectList(Connection conn) {
       ArrayList<Employee> list = new ArrayList<Employee>();
       Statement stmt = null;
       ResultSet rset = null;

       String query = "select * from employee";

       try {
          stmt = conn.createStatement();

          rset = stmt.executeQuery(query);

          while (rset.next()) {
             Employee employee = new Employee();

             employee.setEmpId(rset.getString("emp_id"));
             employee.setEmail(rset.getString("email"));
             employee.setMyProfile(rset.getString("my_profile"));
             employee.setUserId(rset.getString("user_id"));
             employee.setEmpName(rset.getString("emp_name"));
             employee.setUserPwd(rset.getString("user_pwd"));
             employee.setEmpNo(rset.getString("emp_no"));
             employee.setPhone(rset.getString("phone"));
             employee.setAddress(rset.getString("address"));
             employee.setDeptId(rset.getString("DEPT_ID"));
             employee.setJobId(rset.getString("JOB_ID"));
             employee.setPaystep(rset.getString("paystep"));
             employee.setEmpPhone(rset.getString("emp_phone"));
             employee.setSalary(rset.getInt("salary"));
             employee.setBonus(rset.getDouble("bonus"));
             employee.setMarriage(rset.getString("marriage"));
             employee.setHireDate(rset.getDate("hire_date"));
             employee.setIdLevel(rset.getString("id_level"));
             employee.setOriginalSign(rset.getString("ORIGINAL_SIGN"));
             employee.setReNameSign(rset.getString("RENAME_SIGN"));

             list.add(employee);
          }
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          close(rset);
          close(stmt);
       }
       return list;
    }
    
    
    //조직도 부서 리스트 조회용
    public ArrayList<Dept> dselectList(Connection conn) {
       ArrayList<Dept> dlist = new ArrayList<Dept>();
       Statement stmt = null;
       ResultSet rset = null;
       
       String query = "select * from dept order by dept_id asc";
       
       try {
          stmt = conn.createStatement();
          
          rset = stmt.executeQuery(query);

          while (rset.next()) {
             
             Dept dept = new Dept();
             
             dept.setDeptId(rset.getString("DEPT_ID"));
             dept.setDeptName(rset.getString("DEPT_NAME"));
             dept.setFax(rset.getString("FAX"));
             
             dlist.add(dept);
          }
       } catch (SQLException e) {
          e.printStackTrace();
       }finally {
          close(rset);
          close(stmt);
       }
       return dlist;
    }
    
    
    //조직도 리스트 상세보기 조회용
    public Employee empListDetail(Connection conn, String empid) {
       Employee employee = null;
       PreparedStatement pstmt = null;
       ResultSet rset = null;

       String query = "select emp_id, email, my_profile, user_id, emp_name, user_pwd, emp_no, phone, address, DEPT_ID, JOB_ID, "
                         + "dept_name, job_name, paystep, emp_phone, salary, bonus, marriage, hire_date, id_level, ORIGINAL_SIGN, RENAME_SIGN, "
                            + "fax from EMPLOYEE full join dept using (dept_ID) full join job using (job_id) where emp_id = ?";

       try {
          pstmt = conn.prepareStatement(query);
          pstmt.setString(1, empid);

          rset = pstmt.executeQuery();

          if (rset.next()) {
             employee = new Employee();

             employee.setEmpId(rset.getString("emp_id"));
             employee.setEmail(rset.getString("email"));
             employee.setMyProfile(rset.getString("my_profile"));
             employee.setUserId(rset.getString("user_id"));
             employee.setEmpName(rset.getString("emp_name"));
             employee.setUserPwd(rset.getString("user_pwd"));
             employee.setEmpNo(rset.getString("emp_no"));
             employee.setPhone(rset.getString("phone"));
             employee.setAddress(rset.getString("address"));
             employee.setDeptId(rset.getString("DEPT_ID"));
             employee.setJobId(rset.getString("JOB_ID"));
             employee.setDeptName(rset.getString("dept_name"));
             employee.setJobName(rset.getString("job_name"));
             employee.setPaystep(rset.getString("paystep"));
             employee.setEmpPhone(rset.getString("emp_phone"));
             employee.setSalary(rset.getInt("salary"));
             employee.setBonus(rset.getDouble("bonus"));
             employee.setMarriage(rset.getString("marriage"));
             employee.setHireDate(rset.getDate("hire_date"));
             employee.setIdLevel(rset.getString("id_level"));
             employee.setOriginalSign(rset.getString("ORIGINAL_SIGN"));
             employee.setReNameSign(rset.getString("RENAME_SIGN"));
             employee.setFax(rset.getString("fax"));
          }
       } catch (SQLException e) {
          e.printStackTrace();
       } finally {
          close(rset);
          close(pstmt);
       }
       return employee;
    }

		
		
		public Employee selectOne(Connection conn, String empId) {
			Employee employee = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;

			String query = "select * from employee where empid = ?";

			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, empId);

				rset = pstmt.executeQuery();

				if (rset.next()) {
					employee = new Employee();

					employee.setEmpId(rset.getString("empid"));
					employee.setEmail(rset.getString("email"));
					employee.setMyProfile(rset.getString("myprofile"));
					employee.setUserId(rset.getString("userid"));
					employee.setEmpName(rset.getString("empname"));
					employee.setUserPwd(rset.getString("userpwd"));
					employee.setEmpNo(rset.getString("empno"));
					employee.setPhone(rset.getString("phone"));
					employee.setAddress(rset.getString("address"));
					employee.setDeptId(rset.getString("deptid"));
					employee.setJobId(rset.getString("jobid"));
					employee.setPaystep(rset.getString("paystep"));
					employee.setEmpPhone(rset.getString("empphone"));
					employee.setSalary(rset.getInt("salary"));
					employee.setBonus(rset.getDouble("bonus"));
					employee.setMarriage(rset.getString("marriage"));
					employee.setHireDate(rset.getDate("hiredate"));
					employee.setIdLevel(rset.getString("idlevel"));
					employee.setOriginalSign(rset.getString("original_sign"));
					employee.setReNameSign(rset.getString("rename_sign"));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}

			return employee;
		}

		public Employee employeeInfo(Connection conn, String empId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Employee emp = null;
			String query = "select * from employee where emp_id = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, empId);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()){
					 emp = new Employee(rset.getString("email"), rset.getString("user_id"),
							rset.getString("emp_name"), rset.getString("user_pwd"), rset.getString("emp_no"),
							rset.getString("phone"), rset.getString("address"), rset.getString("marriage"));
					
					 emp.setHireDate(rset.getDate("hire_date"));
					 emp.setEmpId(rset.getString("emp_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return emp;
		}

		public ArrayList<Employee> searchDeptId(Connection conn) {
			ArrayList<Employee> list = new ArrayList<Employee>();
			Statement stmt = null;
			ResultSet rset = null;
			String query="select * from dept";
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				while(rset.next()) {
					Employee emp = new Employee();
					emp.setDeptId(rset.getString("dept_id"));
					emp.setDeptName(rset.getString("dept_name"));
					
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

		public ArrayList<Employee> searchJobId(Connection conn) {
			ArrayList<Employee> list = new ArrayList<Employee>();
			Statement stmt = null;
			ResultSet rset = null;
			String query="select * from job";
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				while(rset.next()) {
					Employee emp = new Employee();
					emp.setJobId(rset.getString("job_id"));
					emp.setJobName(rset.getString("job_name"));
					
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

		public ArrayList<Employee> searchPaystep(Connection conn) {
			ArrayList<Employee> list = new ArrayList<Employee>();
			Statement stmt = null;
			ResultSet rset = null;
			String query="select * from paystep";
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				while(rset.next()) {
					Employee emp = new Employee();
					emp.setPaystep(rset.getString("paystep"));
					
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

		public int emailCheck(Connection conn, String email) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select email from employee where email= ?";
			
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
		
		public int phoneCheck(Connection conn, String phone) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = "select phone from employee where phone= ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, phone);		
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
		
		//승인처리 할때 필요함
		public String searchEmpId(Connection conn, String userId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String empId = "";
			String query ="select emp_id from employee where user_id = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, userId);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					empId = rset.getString("emp_id");
				}			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return empId;
		}

		public int updateState(Connection conn, String empId) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query ="insert into state values(?, ?, null, sysdate)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, empId);
				pstmt.setString(2, "재직");	
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();				               
			}finally {			
				close(pstmt);
			}
			return result;
		}

		
	// 이메일에서 주소록 select 해오기 형규
	public ArrayList<Employee> selectAddress(Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select email, emp_name from employee";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Employee emp = new Employee();

				emp.setEmail(rset.getString("email"));
				emp.setEmpName(rset.getString("emp_name"));

				list.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}
}
