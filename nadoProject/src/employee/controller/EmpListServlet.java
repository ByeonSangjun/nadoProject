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
import employee.model.vo.Dept;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/emplist")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 전체 조회 처리용 컨트롤러
		
				HttpSession session = request.getSession();
				
				//조직도 부서 리스트용
				ArrayList<Dept> dlist = new EmployeeService().dselectList(session.getAttribute("comp"));
				//조직도 사원 리스트용
				ArrayList<Employee> list = new EmployeeService().selectList(session.getAttribute("comp"));
						
				RequestDispatcher view = null;
					if(list.size() > 0) {
						view = request.getRequestDispatcher("views/groupware/employee/empListView.jsp");
						request.setAttribute("list", list);
						request.setAttribute("dlist", dlist);
						view.forward(request, response);
					}else {
						view = request.getRequestDispatcher("views/common/error.jsp");
						request.setAttribute("message", "회원 전체 조회 실패!");
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
