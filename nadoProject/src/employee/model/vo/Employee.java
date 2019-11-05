package employee.model.vo;

import java.sql.Date;

public class Employee implements java.io.Serializable{
	private static final long serialVersionUID = 100L;

	private String empId;
	private String email;
	private String myProfile;
	private String userId;
	private String empName;
	private String userPwd;
	private String empNo;
	private String phone;
	private String address;
	private String deptId;
	private String jobId;
	private String paystep;
	private String empPhone;
	private int salary;
	private double bonus;
	private String marriage;
	private java.sql.Date hireDate;
	private String idLevel;
	private String deptName;
	private String jobName;
	private String originalSign;
	private String reNameSign;
	private String fax;
	
	public Employee() {}
	
	//가입 요청시에 사용
	public Employee(String email, String userId, String empName, String userPwd, String empNo, String phone,
			String address, String marriage) {
		super();
		this.email = email;
		this.userId = userId;
		this.empName = empName;
		this.userPwd = userPwd;
		this.empNo = empNo;
		this.phone = phone;
		this.address = address;
		this.marriage = marriage;
	}



	
	public Employee(String empId, String email, String myProfile, String userId, String empName, String userPwd,
			String empNo, String phone, String address, String deptId, String jobId, String paystep, String empPhone,
			int salary, double bonus, String marriage, Date hireDate, String idLevel, String deptName, String jobName, String originalSign, String reNameSign, String fax) {
		super();
		this.empId = empId;
		this.email = email;
		this.myProfile = myProfile;
		this.userId = userId;
		this.empName = empName;
		this.userPwd = userPwd;
		this.empNo = empNo;
		this.phone = phone;
		this.address = address;
		this.deptId = deptId;
		this.jobId = jobId;
		this.paystep = paystep;
		this.empPhone = empPhone;
		this.salary = salary;
		this.bonus = bonus;
		this.marriage = marriage;
		this.hireDate = hireDate;
		this.idLevel = idLevel;
		this.deptName = deptName;
		this.jobName = jobName;
		this.originalSign = originalSign;
		this.reNameSign = reNameSign;
		this.fax = fax;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMyProfile() {
		return myProfile;
	}

	public void setMyProfile(String myProfile) {
		this.myProfile = myProfile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getPaystep() {
		return paystep;
	}

	public void setPaystep(String paystep) {
		this.paystep = paystep;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(String idLevel) {
		this.idLevel = idLevel;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getOriginalSign() {
		return originalSign;
	}

	public void setOriginalSign(String originalSign) {
		this.originalSign = originalSign;
	}
	
	public String getReNameSign() {
		return reNameSign;
	}

	public void setReNameSign(String reNameSign) {
		this.reNameSign = reNameSign;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", email=" + email + ", myProfile=" + myProfile + ", userId=" + userId
				+ ", empName=" + empName + ", userPwd=" + userPwd + ", empNo=" + empNo + ", phone=" + phone
				+ ", address=" + address + ", deptId=" + deptId + ", jobId=" + jobId + ", paystep=" + paystep
				+ ", empPhone=" + empPhone + ", salary=" + salary + ", bonus=" + bonus + ", marriage=" + marriage
				+ ", hireDate=" + hireDate + ", idLevel=" + idLevel + ", deptName=" + deptName
				+ ", jobName=" + jobName + ", originalSign=" + originalSign + ", reNameSign=" + reNameSign + ", fax=" + fax + "]";
	}

}
