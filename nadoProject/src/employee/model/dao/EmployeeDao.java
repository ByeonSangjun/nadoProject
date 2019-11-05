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
            emp.setSign(rset.getString("sign"));
            emp.setDeptName(rset.getString("dept_name"));
            emp.setJobName(rset.getString("job_name"));
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
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   // 우수

      public ArrayList<Employee> selectList(Connection conn) {
         ArrayList<Employee> list = new ArrayList<Employee>();
         Statement stmt = null;
         ResultSet rset = null;

         String query = "select emp_id, email, my_profile, user_id, emp_name, user_pwd, emp_no, "
               + "phone, address, dept_name, job_name, paystep, emp_phone, salary, bonus, marriage, "
               + "hire_date, id_level,sign from employee "
               + "LEFT JOIN dept USING (dept_ID) left join job using (job_id)";

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
               employee.setDeptId(rset.getString("dept_name"));
               employee.setJobId(rset.getString("job_name"));
               employee.setPaystep(rset.getString("paystep"));
               employee.setEmpPhone(rset.getString("emp_phone"));
               employee.setSalary(rset.getInt("salary"));
               employee.setBonus(rset.getDouble("bonus"));
               employee.setMarriage(rset.getString("marriage"));
               employee.setHireDate(rset.getDate("hire_date"));
               employee.setIdLevel(rset.getString("id_level"));
               employee.setSign(rset.getString("sign"));

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
               employee.setSign(rset.getString("sign"));

            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(rset);
            close(pstmt);
         }

         return employee;
      }

      
      
      public ArrayList<Dept> selectgwList(Connection conn) {
         ArrayList<Dept> dlist = new ArrayList<Dept>();
         Statement stmt = null;
         ResultSet rset = null;
         
         String query = "select emp_name, dept_id, DEPT_NAME, DEPT_ORIGINID, DEPT_LEVEL, FAX " + 
               "from dept " + 
               "left join employee using (dept_id)";

         try {
            stmt = conn.createStatement();

            rset = stmt.executeQuery(query);

            while (rset.next()) {
               
               Dept dept = new Dept();
               
               dept.setDeptId(rset.getString("dept_id"));
               dept.setDeptName(rset.getString("dept_name"));
               dept.setDeptOriginId(rset.getString("dept_originid"));
               dept.setDeptLevel(rset.getInt("dept_level"));
               dept.setFax(rset.getString("fax"));
               

               dlist.add(dept);

            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(rset);
            close(stmt);
         }

         return dlist;

      }
   
   
   
   

}