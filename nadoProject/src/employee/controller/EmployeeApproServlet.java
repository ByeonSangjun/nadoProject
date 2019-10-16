package employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeApproServlet
 */
@WebServlet("/empapp.ad")
public class EmployeeApproServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeApproServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사원 가입 승인 처리 관리자
				request.setCharacterEncoding("utf-8");
				
				Employee emp = new Employee();	
				emp.setUserId(request.getParameter("userid"));
				emp.setEmpId(request.getParameter("empid"));
				emp.setIdLevel(request.getParameter("idlevel"));
				emp.setDeptId(request.getParameter("deptid"));
				emp.setJobId(request.getParameter("jobid"));
				emp.setPaystep(request.getParameter("paystep"));
				
				HttpSession session = request.getSession();
				int result = new EmployeeService().approEmployee(session.getAttribute("comp"), emp);
				
				if(result >0) {
					response.sendRedirect("/nado/empic.ad");
					
				}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
