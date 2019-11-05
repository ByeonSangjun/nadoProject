package chatting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chatting.model.service.ChattingService;
import chatting.model.vo.Chatting;
import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class ChattingMainServlet
 */
@WebServlet("/chtm")
public class ChattingMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 채팅메인 서블릿
		//회사 조회
		HttpSession session = request.getSession(false);
		
		Object comp = session.getAttribute("comp");
		//내 사번
		String empId = request.getParameter("emp_id");
		
		ChattingService chServ = new ChattingService();
		//모든 사원조회
		ArrayList<Employee> empList = new EmployeeService().selectList(comp);
		
		//1. 내가 참여한 방 정보들 (방번호, 사번, 방제목)
		ArrayList<Chatting> chatList = chServ.myRoomList(comp, empId);
		
		//3. 참여방들에 마지막 글
		ArrayList<Chatting> lastContents = chServ.searchLastContents(comp, empId);
		
		if(chatList.size()>0) {
			
			RequestDispatcher view = request.getRequestDispatcher("views/groupware/chatting/chattingMain.jsp");
			request.setAttribute("chatList", chatList);
			request.setAttribute("empList", empList);
			request.setAttribute("lastContents", lastContents);
			view.forward(request, response);
		}else {
			System.out.println("오류");
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
