package chatting.model.vo;

import java.sql.Date;

public class Chatting implements java.io.Serializable{
	private static final long serialVersionUID = 1200L;
	
	private String empId; 
	private int chatRoomNo;
	private String email;
	private String myProfile;
	private String userId;
	private String empName;
	private String empNo;
	private String originalSign;
	private String reNameSign;
	private int chatNo;
	private String chatContent;
	private java.sql.Date chatSendTime;
	private String chatTitle;
	

	public Chatting() {}

	public Chatting(String empId, int chatRoomNo, String email, String myProfile, String userId, String empName,
			String empNo, String originalSign, String reNameSign, int chatNo, String chatContent, Date chatSendTime,
			String chatTitle) {
		super();
		this.empId = empId;
		this.chatRoomNo = chatRoomNo;
		this.email = email;
		this.myProfile = myProfile;
		this.userId = userId;
		this.empName = empName;
		this.empNo = empNo;
		this.originalSign = originalSign;
		this.reNameSign = reNameSign;
		this.chatNo = chatNo;
		this.chatContent = chatContent;
		this.chatSendTime = chatSendTime;
		this.chatTitle = chatTitle;
		
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
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

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public java.sql.Date getChatSendTime() {
		return chatSendTime;
	}

	public void setChatSendTime(java.sql.Date chatSendTime) {
		this.chatSendTime = chatSendTime;
	}

	public String getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}
	
	@Override
	public String toString() {
		return "Chatting [empId=" + empId + ", chatRoomNo=" + chatRoomNo + ", email=" + email + ", myProfile="
				+ myProfile + ", userId=" + userId + ", empName=" + empName + ", empNo=" + empNo + ", originalSign="
				+ originalSign + ", reNameSign=" + reNameSign + ", chatNo=" + chatNo + ", chatContent=" + chatContent
				+ ", chatSendTime=" + chatSendTime + ", chatTitle=" + chatTitle + "]";
	}
	
}
