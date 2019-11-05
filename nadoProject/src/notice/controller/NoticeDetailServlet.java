package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeAdminDetailServlet
 */
@WebServlet("/ndetail.ad")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자용 공지글 상세보기 처리용 컨트롤러
		
		HttpSession session = request.getSession();
		
		int noticeNo = Integer.parseInt(request.getParameter("no"));
		
		NoticeService nservice = new NoticeService();
		nservice.updateReadCount(session.getAttribute("comp"), noticeNo);
		Notice notice = nservice.selectOne(session.getAttribute("comp"), noticeNo);
		
		RequestDispatcher view = null;
		if(notice != null) {
			view = request.getRequestDispatcher("views/groupware/notice/noticeDetailView.jsp");
			request.setAttribute("notice", notice);
		}else {
			view = request.getRequestDispatcher("views/common.error.jsp");
			request.setAttribute("message", noticeNo + "번 공지 상세보기 실패!");
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
