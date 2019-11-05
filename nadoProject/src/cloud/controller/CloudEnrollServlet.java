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

import cloud.model.service.CloudService;
import cloud.model.vo.Cloud;
import employee.model.vo.Company;

/**
 * Servlet implementation class CloudEnrollServlet
 */
@WebServlet("/cler")
public class CloudEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloudEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//받아온 정보꺼내기
		String empName = request.getParameter("emp_name");
			
		String userId = request.getParameter("user_id");
	
		String userPwd = request.getParameter("user_pwd");

		String phone = request.getParameter("phone");
	
		String email = request.getParameter("email");
	
		String postcode = request.getParameter("postcode");
		
		String address = request.getParameter("address");
	
		String detailAddress = request.getParameter("detailAddress");
		
		String extraAddress = request.getParameter("sample6_extraAddress");
		
		String fullAddress = null;
		
	
		if(extraAddress != "" && detailAddress != "") {
			fullAddress = postcode +"%"+ address +"%"+ extraAddress+"%"+ detailAddress;
		}else if(extraAddress == "" && detailAddress != "") {
			fullAddress = postcode +"%"+ address +"%"+ detailAddress;
		}else if(extraAddress != "" && detailAddress == "") {
			fullAddress = postcode +"%"+ address +"%"+ extraAddress;
		}else {
			fullAddress = postcode +"%"+ address;
		}

		Cloud cloud = new Cloud();
		
		cloud.setClName(empName);
		cloud.setClId(userId);
		cloud.setClPwd(userPwd);
		cloud.setClPhone(phone);
		cloud.setClEmail(email);
		cloud.setClAddress(fullAddress);

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
	
		int result = new CloudService().enrollCloud(comp, cloud);
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
