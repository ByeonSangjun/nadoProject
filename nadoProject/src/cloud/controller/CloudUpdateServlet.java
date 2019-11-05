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
 * Servlet implementation class CloudUpdateServlet
 */
@WebServlet("/cluv")
public class CloudUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloudUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//받아온 정보꺼내기
		int clNo = Integer.parseInt(request.getParameter("cl_no"));
		String empName = request.getParameter("emp_name");
			System.out.println(empName);
		String userId = request.getParameter("user_id");
		System.out.println(userId);
		String userPwd = request.getParameter("user_pwd");
		System.out.println(userPwd);
		String phone = request.getParameter("phone");
		System.out.println(phone);
		String email = request.getParameter("email");
		System.out.println(email);
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
		
		//꺼낸값 객체에 담기
//		Employee emp = new Employee(email, userId, empName, userPwd, empNo, phone, address, marriage);
		Cloud cloud = new Cloud();
		cloud.setClNo(clNo);
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
	
		int result = new CloudService().updateCloud(comp, cloud);
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
