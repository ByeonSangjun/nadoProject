package cloud.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

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
 * Servlet implementation class CloudCheckPwd
 */
@WebServlet("/clcp")
public class CloudCheckPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloudCheckPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clUserPwd = request.getParameter("user_pwd");
		String clId = request.getParameter("user_id");
		// 프로퍼티스 생성
		Properties prop = new Properties();

		// 루트 경로에 있는 properties 읽기
		String filePath = request.getSession().getServletContext().getRealPath("/logindb/company.properties");
		prop.load(new FileReader(filePath));

		String driver = prop.getProperty("nadodriver");
		String url = prop.getProperty("nadourl");
		String id = prop.getProperty("nadoid");
		String password = prop.getProperty("nadopassword");

		// jdbc에 정보 저장
		Company comp = new Company(driver, url, id, password);

		Cloud cloud = new CloudService().checkPwd(comp, clId, clUserPwd);
		
		int result = 0;
		if(cloud != null) {
			result = 1;
		}
		HttpSession session  = request.getSession(false);
		session.setAttribute("loginUser", cloud);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
