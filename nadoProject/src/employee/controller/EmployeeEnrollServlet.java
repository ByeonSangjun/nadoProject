package employee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeEnrollServlet
 */
@WebServlet("/emper")
public class EmployeeEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//받아온 정보꺼내기
		String empName = request.getParameter("emp_name");
		String userId = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String fullEmpNo = request.getParameter("emp_no");
		String empNo = fullEmpNo.substring(0, 6) +"-" +fullEmpNo.substring(6);
		String marriage = request.getParameter("marriage");
		String extraAddress = request.getParameter("sample6_extraAddress");
		String address = null;
		if(extraAddress != null) {
			address = "우편번호(" +request.getParameter("postcode") +") "+ request.getParameter("address")+request.getParameter("sample6_extraAddress")+" " + request.getParameter("detailAddress");	
		}else {
			address = "우편번호(" +request.getParameter("postcode") +") "+ request.getParameter("address")+" " + request.getParameter("detailAddress");
		}
		
		//꺼낸값 객체에 담기
		Employee emp = new Employee(email, userId, empName, userPwd, empNo, phone, address, marriage);
		HttpSession session = request.getSession();
		int result = new EmployeeService().enrollEmployee(session.getAttribute("comp"), emp);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
