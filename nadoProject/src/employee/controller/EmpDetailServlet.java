package employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import employee.model.service.EmployeeService;
import employee.model.vo.Employee;

/**
 * Servlet implementation class EmpDetailServlet
 */
@WebServlet("/edetail")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 조직도 상세보기 처리용 컨트롤러
		
		HttpSession session = request.getSession();
		
		String empid = request.getParameter("empid");
		//System.out.println("empid : " + empid);
		
		// 전송온 값 받고, 뷰로 자바 객체 정보를 자바스크립트 객체(JSON)로 바꾸어 리턴 처리
		//자바에서는 기본적으로 자바스크립트 객체를 다룰 클래스가 제공이 안됨.
		//별도의 라이브러리 찾아서 다운받아서 사용해야 함.
		//자바 객체 생성
		
		EmployeeService eservice = new EmployeeService();
		Employee employee = eservice.empListDetail(session.getAttribute("comp"), empid);
		//System.out.println("employee : " + employee.toString());
		
		//자바스크립트 객체로 바꿈 : 별도의 라이브러리가 제공하는 클래스 사용함
				JSONObject job = new JSONObject();
				//map 구조로 값 기록함
				//json 에서 한글깨짐을 막으려면 인코딩 처리해야 함
				//java.net.URLEncoder 클래스의 encode() 사용함
				//job.put("empId", employee.getEmpId());
				job.put("email", URLEncoder.encode(employee.getEmail(), "UTF-8"));
				String myProfile = employee.getMyProfile();
				if (myProfile != null) {
					job.put("myProfile", URLEncoder.encode(employee.getMyProfile(), "UTF-8"));
				} else {
					job.put("myProfile", "default-profile.png");
				}
				job.put("userId", URLEncoder.encode(employee.getUserId(), "UTF-8"));
				job.put("empName", URLEncoder.encode(employee.getEmpName(), "UTF-8"));
				//job.put("userPwd", URLEncoder.encode(employee.getUserPwd(), "UTF-8"));
				//job.put("empNo", employee.getEmpNo());
				job.put("phone", employee.getPhone());
				//job.put("address", URLEncoder.encode(employee.getAddress(), "UTF-8"));
				//job.put("deptId", employee.getDeptId());
				//job.put("jobId", employee.getJobId());
				job.put("deptName", URLEncoder.encode(employee.getDeptName(), "UTF-8"));
				job.put("jobName", URLEncoder.encode(employee.getJobName(), "UTF-8"));
				//job.put("paystep", URLEncoder.encode(employee.getPaystep(), "UTF-8"));
				job.put("empPhone", employee.getEmpPhone());
				//job.put("salary", employee.getEmpId());
				//job.put("bonus", employee.getEmpId());
				//job.put("marriage", employee.getMarriage());
				//job.put("hireDate", employee.getHireDate().toString());		//날짜는 반드시 문자열 형태로 바꿔야 함		
				//job.put("idLevel", employee.getIdLevel());
				//job.put("originalSign", URLEncoder.encode(employee.getOriginalSign(), "UTF-8"));
				//job.put("renameSign", URLEncoder.encode(employee.getReNameSign(), "UTF-8"));
				job.put("fax", employee.getFax());
				
				//확인
				System.out.println("job : " + job.toJSONString());
				
				//요청한 ajax 뷰로 json 객체 하나를 전송함
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(job.toJSONString());	//객체 정보를 문자열로 바꾸어 내보냄.
				out.flush();
				out.close();
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
