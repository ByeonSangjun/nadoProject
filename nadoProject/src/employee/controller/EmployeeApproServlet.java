package employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
				EmployeeService eServ = new EmployeeService();
				
				String userId = request.getParameter("user_id");
				
				Employee emp = new Employee();	
				emp.setUserId(userId);
				emp.setEmpId(request.getParameter("emp_id"));
				emp.setIdLevel(request.getParameter("id_level"));
				emp.setDeptId(request.getParameter("dept_id"));
				emp.setJobId(request.getParameter("job_id"));
				emp.setPaystep(request.getParameter("paystep"));			
	
				HttpSession session = request.getSession();
				Object comp = session.getAttribute("comp");
				
				int result = eServ.approEmployee(comp, emp);
	
				if(result >0) {
					String empId = eServ.searchEmpId(comp, userId);	
					int result2 = eServ.updateState(comp, empId);
					if(result2 > 0) {
						PrintWriter pw = response.getWriter();
						String str = "";
						str = "<script language='javascript'>";
			            str += "opener.window.location.reload();";  //오프너 새로고침
			            str += "self.close();";   // 창닫기
			            str += "</script>";
			            pw.print(str);
			            pw.flush();
			            pw.close();
					}else {
						RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
				         view.forward(request, response);
					}		
				}else {
					System.out.println("첫번째");
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			         view.forward(request, response);
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
