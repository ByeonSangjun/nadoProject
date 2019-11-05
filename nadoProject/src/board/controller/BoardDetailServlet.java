package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.Comments;



/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/bdetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 상세보기 처리용 컨트롤러
		
		HttpSession session = request.getSession();
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		BoardService bservice = new BoardService();
		bservice.updateReadCount(session.getAttribute("comp"), boardNo);
		Board board = bservice.selectOne(session.getAttribute("comp"), boardNo);
		
		
		ArrayList<Comments> list = bservice.replylist(session.getAttribute("comp"), boardNo);
		
		
		
		RequestDispatcher view = null;
		if(board != null) {
			view = request.getRequestDispatcher("views/groupware/board/boardDetailView.jsp");
			request.setAttribute("board", board);
			request.setAttribute("list", list);
		}else {
			view = request.getRequestDispatcher("views/common.error.jsp");
			request.setAttribute("message", boardNo + "번 공지 상세보기 실패!");
		}
		
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
