package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{
	private static final long serialVersionUID = 500L;
	
	private int noticeNo;		//공지사항 글 번호
	private String userId;	//작성자 아이디
	private String noticeTitle;	//제목
	private java.sql.Date noticeDate;	//작성일
	private int noticeReadCount;		//조회수
	private String noticeContent;		//내용
	private String nfileOriname;	//오리지널 파일명
	private String nfileRename;	//리네임 파일명
	
	public Notice() {}

	public Notice(int noticeNo, String userId, String noticeTitle, Date noticeDate, int noticeReadCount,
			String noticeContent, String nfileOriname, String nfileRename) {
		super();
		this.noticeNo = noticeNo;
		this.userId = userId;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeReadCount = noticeReadCount;
		this.noticeContent = noticeContent;
		this.nfileOriname = nfileOriname;
		this.nfileRename = nfileRename;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getNoticeReadCount() {
		return noticeReadCount;
	}

	public void setNoticeReadCount(int noticeReadCount) {
		this.noticeReadCount = noticeReadCount;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNfileOriname() {
		return nfileOriname;
	}

	public void setNfileOriname(String nfileOriname) {
		this.nfileOriname = nfileOriname;
	}

	public String getNfileRename() {
		return nfileRename;
	}

	public void setNfileRename(String nfileRename) {
		this.nfileRename = nfileRename;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", userId=" + userId + ", noticeTitle=" + noticeTitle + ", noticeDate="
				+ noticeDate + ", noticeReadCount=" + noticeReadCount + ", noticeContent=" + noticeContent
				+ ", nfileOriname=" + nfileOriname + ", nfileRename=" + nfileRename + "]";
	}

	
	
}
