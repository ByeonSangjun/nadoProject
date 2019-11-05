package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Comments;

/**
 * Servlet implementation class BoardReplyInsertServlet
 */
@WebServlet("/breplyinsert")
public class BoardReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 입력 컨트롤러
				request.setCharacterEncoding("utf-8");
			      Comments comments = new Comments();
			      comments.setUserId(request.getParameter("replyuser"));
			      comments.setCmContent(request.getParameter("replycontent"));
			      comments.setCmRef(Integer.parseInt(request.getParameter("boardno")));
			      
			      HttpSession session = request.getSession();
			      
			      int result = new BoardService().insertComments(session.getAttribute("comp"), comments);
			      
			      if(result > 0) {
			         response.setContentType("text/html; charset=utf-8");

			         PrintWriter out = response.getWriter();
			         out.println("<script>");
			         //out.println("window.location.reload();");
			         out.println("window.location = document.referrer;");
			         out.println("</script>");
			      }else {
			         /*response.setContentType("text/html; charset=utf-8");

			         PrintWriter out = response.getWriter();
			         out.println("<script>");
			         out.println("alert('댓글 등록 실패!');");
			         out.println("window.location = document.referrer;");
			         out.println("</script>");*/
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
