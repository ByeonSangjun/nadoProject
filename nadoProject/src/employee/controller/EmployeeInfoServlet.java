package employee.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeInfoServlet
 */
@WebServlet("/empif.ad")
public class EmployeeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		Object comp = session.getAttribute("comp");
		String empId = request.getParameter("empId");
		//사용자 승인관리 확인 후 승인 누르면 뜨는 자식창
		EmployeeService eServ = new EmployeeService();
		Employee emp = eServ.employeeInfo(comp, empId);
		ArrayList<Employee> deptList = eServ.searchDeptId(comp);
		ArrayList<Employee> jobList = eServ.searchJobId(comp);
		ArrayList<Employee> paystep = eServ.searchPaystep(comp);
		if(emp != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/groupware/manager/employeeBeforeApproval.jsp");
			request.setAttribute("empInfo", emp);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("paystep", paystep);
			view.forward(request, response);
		}else {
			System.out.println("실패");
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
