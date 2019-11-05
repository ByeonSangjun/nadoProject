package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeAdminDeleteServlet
 */
@WebServlet("/ndel.ad")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지글 삭제 처리용 컨트롤러
				//1.
				request.setCharacterEncoding("utf-8");
				
				//2.
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				String renameFileName = request.getParameter("rfile");
				
				//3.
				
				HttpSession session = request.getSession(false);
				
				int result = new NoticeService().deleteNotice(session.getAttribute("comp"), noticeNo);
				
				//4.
				if(result > 0) {
					//공지글 삭제 성공시에 첨부되었던 파일 삭제 처리함
					//공지글 삭제이므로, 첨부된 파일이 있다면 저장폴더에서 찾아서 삭제함
					if(renameFileName != null) {
						String savePath = request.getSession().getServletContext().getRealPath("/resources/groupware/notice_upfiles");
						File renameFile = new File(savePath + "\\" + renameFileName);
						renameFile.delete();
					}
					
					response.sendRedirect("/nado/nlist.ad");
					
				}else {
					RequestDispatcher view = request.getRequestDispatcher("views/common/error.jsp");
					request.setAttribute("message", noticeNo + "번 공지글 삭제 실패!");
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
