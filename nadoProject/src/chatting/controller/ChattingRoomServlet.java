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
import employee.model.vo.Employee;

/**
 * Servlet implementation class ChattingRoomServlet
 */
@WebServlet("/chtor")
public class ChattingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		request.setCharacterEncoding("utf-8");
//		
//		String chatNo = request.getParameter("chno");
//		int chatRoomNo = Integer.parseInt(chatNo);
//		String empId = request.getParameter("eid"); 
//		
//		ChattingService cServ = new ChattingService();
//		HttpSession session = request.getSession(false);
//		Object comp = session.getAttribute("comp");
//		RequestDispatcher view= null;
//		
//		Chatting chatRoom = cServ.searchThisRoom2(comp, chatRoomNo, empId);  //채팅방 정보
//		ArrayList<Chatting> contentList = cServ.selectAllContent(comp, chatRoomNo);
//		String roomUsers = cServ.roomUsers(comp, chatRoomNo);
//		
//		if(contentList.size()>0) {
//			//전체 글 조회 성공			
//			view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
//			//전체 글 보냄 (chat_no, chat_room_no, emp_id, chat_content, chat_send_time)
//			request.setAttribute("contentList", contentList);
//			//방정보 보냄
//			request.setAttribute("chatRoom", chatRoom);	
//			//방 참여 인원들 보냄
//			request.setAttribute("roomUsers", roomUsers);		
//			view.forward(request, response);		
//		}else {
//			//전체 글 조회 실패 (방만 만들고 글 안보낸경우도 있음)
//			view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
//			//방정보 보냄
//			request.setAttribute("contentList", contentList);
//			request.setAttribute("chatRoom", chatRoom);
//			//방 참여 인원들 보냄
//			request.setAttribute("roomUsers", roomUsers);
//			view.forward(request, response);
//			System.out.println("전체 글 조회 실패");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
