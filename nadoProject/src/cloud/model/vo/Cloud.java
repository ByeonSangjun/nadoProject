package cloud.model.vo;

import java.sql.Date;

public class Cloud implements java.io.Serializable{
	private static final long serialVersionUID = 1300L;
	
	private int clNo;
	private String clName;
	private String clId;
	private String clPwd;
	private String clPhone;
	private String clEmail;
	private String clAddress;
	private java.sql.Date clDate;
	
	public Cloud() {}

	public Cloud(int clNo, String clName, String clId, String clPwd, String clPhone, String clEmail, String clAddress,
			Date clDate) {
		super();
		this.clNo = clNo;
		this.clName = clName;
		this.clId = clId;
		this.clPwd = clPwd;
		this.clPhone = clPhone;
		this.clEmail = clEmail;
		this.clAddress = clAddress;
		this.clDate = clDate;
	}

	public int getClNo() {
		return clNo;
	}

	public void setClNo(int clNo) {
		this.clNo = clNo;
	}

	public String getClName() {
		return clName;
	}

	public void setClName(String clName) {
		this.clName = clName;
	}

	public String getClId() {
		return clId;
	}

	public void setClId(String clId) {
		this.clId = clId;
	}

	public String getClPwd() {
		return clPwd;
	}

	public void setClPwd(String clPwd) {
		this.clPwd = clPwd;
	}

	public String getClPhone() {
		return clPhone;
	}

	public void setClPhone(String clPhone) {
		this.clPhone = clPhone;
	}

	public String getClEmail() {
		return clEmail;
	}

	public void setClEmail(String clEmail) {
		this.clEmail = clEmail;
	}

	public String getClAddress() {
		return clAddress;
	}

	public void setClAddress(String clAddress) {
		this.clAddress = clAddress;
	}

	public java.sql.Date getClDate() {
		return clDate;
	}

	public void setClDate(java.sql.Date clDate) {
		this.clDate = clDate;
	}

	@Override
	public String toString() {
		return "Cloud [clNo=" + clNo + ", clName=" + clName + ", clId=" + clId + ", clPwd=" + clPwd + ", clPhone="
				+ clPhone + ", clEmail=" + clEmail + ", clAddress=" + clAddress + ", clDate=" + clDate + "]";
	}

	
	
	

}
