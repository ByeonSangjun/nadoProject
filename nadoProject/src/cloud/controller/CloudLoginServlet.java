package cloud.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cloud.model.service.CloudService;
import cloud.model.vo.Cloud;
import employee.model.vo.Company;

/**
 * Servlet implementation class CloudLoginServlet
 */
@WebServlet("/clli")
public class CloudLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloudLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clId = request.getParameter("cloud_user_id");
		String clPwd = request.getParameter("cloud_user_pwd");
		
		
		// 프로퍼티스 생성
		Properties prop = new Properties();

		// 루트 경로에 있는 properties 읽기
		String filePath = request.getSession().getServletContext().getRealPath("/logindb/company.properties");
		prop.load(new FileReader(filePath));

		String driver = prop.getProperty("nadodriver");
		String url = prop.getProperty("nadourl");
		String id = prop.getProperty("nadoid");
		String password = prop.getProperty("nadopassword");

		// nadodriver 키 값으로 조회했을때 값이 있으면 나머지도 실행

		// jdbc에 정보 저장
		Company comp = new Company(driver, url, id, password);
		HttpSession session = request.getSession();
		session.setAttribute("comp", comp);
				
		// 클라우드 회원 정보 꺼내기
		Cloud loginCloudUser = new CloudService().loginCloud(session.getAttribute("comp"), clId);
		
		PrintWriter pw = null;
		RequestDispatcher view = null;
		if (loginCloudUser != null && loginCloudUser.getClPwd().equals(clPwd)) { // 아이디 패스워드 있음
			session.setAttribute("loginUser", loginCloudUser); // 새션에 정보 담기
			pw = response.getWriter();
			pw.print(1);
			pw.flush();
			pw.close();
		} else if (loginCloudUser != null && !loginCloudUser.getClPwd().equals(clPwd)) {
			pw = response.getWriter();
			pw.print(2); // 2이면 패스워드 틀림
			pw.flush();
			pw.close();
		} else {
			pw = response.getWriter();
			pw.print(3); // 3이면 아이디없음
			pw.flush();
        	pw.close();
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
