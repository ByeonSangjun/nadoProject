package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;


/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/bdel")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 삭제 처리용 컨트롤러
		//1.
		request.setCharacterEncoding("utf-8");
		
		//2.
		int boardNo = Integer.parseInt(request.getParameter("no"));
		String renameFileName = request.getParameter("rfile");
		
		//3.
		BoardService bservice = new BoardService();
		
		HttpSession session = request.getSession(false);
		//게시글 삭제시 게시글안의 댓글 삭제
		bservice.deleteBoardReply(session.getAttribute("comp"), boardNo);
		//게시글 삭제
		int result = bservice.deleteBoard(session.getAttribute("comp"), boardNo);
		
		//4.
		if(result > 0) {
			//게시글 삭제 성공시에 첨부되었던 파일 삭제 처리함
			//게시글 삭제이므로, 첨부된 파일이 있다면 저장폴더에서 찾아서 삭제함
			if(renameFileName != null) {
				String savePath = request.getSession().getServletContext().getRealPath("/resources/groupware/board_upfiles");
				File renameFile = new File(savePath + "\\" + renameFileName);
				renameFile.delete();
			}
			
			response.sendRedirect("/nado/blist");
			
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
			request.setAttribute("message", boardNo + "번 공지글 삭제 실패!");
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
