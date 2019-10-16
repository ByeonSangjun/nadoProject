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
 * Servlet implementation class EmployeeLoginServlet
 */
@WebServlet("/empli")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pwd");
		HttpSession session = request.getSession();
		// 사원한명 정보 꺼내오기
		Employee loginEmp = new EmployeeService().loginEmployee(session.getAttribute("comp"), userId);

		// 사원정보 꺼냈으면 보여줄 뷰 선택
		PrintWriter pw = null;
		RequestDispatcher view = null;
		if (loginEmp != null && loginEmp.getUserPwd().equals(userPwd)) { // 아이디 패스워드 있음
			if (loginEmp.getIdLevel() != null) { // 승인된 아이디
				session.setAttribute("loginEmp", loginEmp); // 새션에 정보 담기
				pw = response.getWriter();
				pw.print(1);
				pw.flush();
				pw.close();
			} else {
				pw = response.getWriter();
				pw.print(2); // 2이면 승인 대기중인 아이디
				pw.flush();
				pw.close();
			}
		} else if (loginEmp != null && !loginEmp.getUserPwd().equals(userPwd)) {
			pw = response.getWriter();
			pw.print(3); // 3이면 패스워드 틀림
			pw.flush();
        	pw.close();
		} else {
			pw = response.getWriter();
			pw.print(4); // 4이면 아이디없음
			pw.flush();
        	pw.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
