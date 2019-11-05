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
 * Servlet implementation class ChattingRoomOpenServlet
 */
@WebServlet("/chto")
public class ChattingRoomOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChattingService cServ = new ChattingService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingRoomOpenServlet() {
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
		RequestDispatcher view = null;
		
		String empId = request.getParameter("myid");
		String chatRoomNo = request.getParameter("roomNo");

		//방 타이틀
		String chatTitle = cServ.searchRoomInfo(comp, empId, chatRoomNo);
		//방 참여 인원들 보내야됨
		String roomUsers = cServ.roomUsers(comp, Integer.parseInt(chatRoomNo));
		//방번호로 글 조회
		ArrayList<Chatting> contentList = cServ.selectAllContent(comp, Integer.parseInt(chatRoomNo));
		//모든 사원조회
		ArrayList<Employee> empList = new EmployeeService().selectList(comp);
		
		view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
		request.setAttribute("chatRoomNo", chatRoomNo);
		request.setAttribute("contentList", contentList);
		request.setAttribute("chatTitle", chatTitle);
		request.setAttribute("empList", empList);
		//방 참여 인원들 보냄
		request.setAttribute("roomUsers", roomUsers);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
