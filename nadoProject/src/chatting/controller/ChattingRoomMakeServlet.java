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


/**
 * Servlet implementation class ChattingRoomMakeServlet
 */
@WebServlet("/chtrm")
public class ChattingRoomMakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingRoomMakeServlet() {
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
//		String myId = request.getParameter("myid");
//		String frId = request.getParameter("frid");
//		ChattingService cServ = new ChattingService();
//		HttpSession session = request.getSession(false);
//		Object comp = session.getAttribute("comp");
//		RequestDispatcher view = null;
//		
//		// 1. 사번 두개 가지고 가서 방 있는지 확인하는 메소드 
//		Chatting chatRoom = cServ.searchThisRoom(comp, myId, frId);  //확인끝
//		
//		if(chatRoom != null) {
//			//방있음
//			// 2. 1번에서 방 있는지 확인했으면 그 방번호 가지고 와서 전체 글 조회
//			ArrayList<Chatting> contentList = cServ.selectAllContent(comp, chatRoom.getChatRoomNo());  //확인끝
//			
//			//방 참여 인원들
//			String roomUsers = cServ.roomUsers(comp, chatRoom.getChatRoomNo());  //확인끝
//			
//			if(contentList.size()>0) {
//				//전체 글 조회 성공
//		
//				view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
//				//전체 글 보냄 (chat_no, chat_room_no, emp_id, chat_content, chat_send_time)
//				request.setAttribute("contentList", contentList);
//				//방정보 보냄
//				request.setAttribute("chatRoom", chatRoom);
//				//방 참여 인원들 보냄
//				request.setAttribute("roomUsers", roomUsers);
//
//				view.forward(request, response);
//			}else {
//				//전체 글 조회 실패 (방만 만들고 글 안보낸경우도 있음)
//				view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
//				//방정보 보냄
//				request.setAttribute("contentList", contentList);
//				request.setAttribute("chatRoom", chatRoom);
//				//방 참여 인원들 보냄
//				request.setAttribute("roomUsers", roomUsers);
//				view.forward(request, response);
//				System.out.println("전체 글 조회 실패");
//			}
//			
//		}else {
//			//방없음
//			//방 만들어줌
//			int result = cServ.createRoom(comp, myId, frId);
//			if(result > 0) {
//				Chatting chatRoom2 = cServ.searchThisRoom(comp, myId, frId);
//					if(chatRoom2 != null) {
//						//방 참여 인원들 보내야됨
//						String roomUsers = cServ.roomUsers(comp, chatRoom2.getChatRoomNo());
//						//방번호로 글 조회
//						//방엔 글 없음
//						ArrayList<Chatting> contentList = cServ.selectAllContent(comp, chatRoom2.getChatRoomNo());
//						view = request.getRequestDispatcher("views/groupware/chatting/openChat.jsp");
//						request.setAttribute("chatRoom", chatRoom2);
//						request.setAttribute("contentList", contentList);
//						//방 참여 인원들 보냄
//						request.setAttribute("roomUsers", roomUsers);
//						view.forward(request, response);
//					}else {
//						System.out.println("방 조회에서 오류났음");
//					}
//			}else {
//				//방만들기 실패
//				System.out.println("방만들기 실패");
//			}
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
