package employee.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employee.model.vo.Company;

/**
 * Servlet implementation class CompanyLoginServlet
 */
@WebServlet("/compli")
public class CompanyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String company = request.getParameter("company_id");	
		// 프로퍼티스 생성
		Properties prop = new Properties();

		// 루트 경로에 있는 properties 읽기
		String filePath = request.getSession().getServletContext().getRealPath("/logindb/company.properties");
		prop.load(new FileReader(filePath));
	
		String driver = null;
		String url = null;
		String id = null;
		String password = null;
		
		// nadodriver 키 값으로 조회했을때 값이 있으면 나머지도 실행
		if ((driver = prop.getProperty(company + "driver")) != null && (url = prop.getProperty(company + "url")) != null
				&& (id = prop.getProperty(company + "id")) != null && (password = prop.getProperty(company + "password")) != null) {

			// jdbc에 정보 저장
			Company comp = new Company(driver, url, id, password);
			HttpSession session = request.getSession();
			session.setAttribute("comp", comp);
			
			response.sendRedirect("/nado/views/groupware/employeeLogin.jsp");
		}else {
			response.sendRedirect("/nado/views/common/error.jsp");
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
