package board.model.vo;

import java.sql.Date;

public class Comments implements java.io.Serializable {
	private static final long serialVersionUID = 720L;
	
	private int cmNo;		//댓글 번호
	private String userId;	//작성자 아이디
	private String cmContent;		//내용
	private java.sql.Date cmDate;	//작성일
	private int cmRef;	//번호
	private int cmReplyRef;		//참조 댓글 번호
	private int cmReplyLev;	//댓글 단계
	
	public Comments() {}

	public Comments(int cmNo, String userId, String cmContent, Date cmDate, int cmRef, int cmReplyRef, int cmReplyLev) {
		super();
		this.cmNo = cmNo;
		this.userId = userId;
		this.cmContent = cmContent;
		this.cmDate = cmDate;
		this.cmRef = cmRef;
		this.cmReplyRef = cmReplyRef;
		this.cmReplyLev = cmReplyLev;
	}

	public int getCmNo() {
		return cmNo;
	}

	public void setCmNo(int cmNo) {
		this.cmNo = cmNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCmContent() {
		return cmContent;
	}

	public void setCmContent(String cmContent) {
		this.cmContent = cmContent;
	}

	public java.sql.Date getCmDate() {
		return cmDate;
	}

	public void setCmDate(java.sql.Date cmDate) {
		this.cmDate = cmDate;
	}

	public int getCmRef() {
		return cmRef;
	}

	public void setCmRef(int cmRef) {
		this.cmRef = cmRef;
	}

	public int getCmReplyRef() {
		return cmReplyRef;
	}

	public void setCmReplyRef(int cmReplyRef) {
		this.cmReplyRef = cmReplyRef;
	}

	public int getCmReplyLev() {
		return cmReplyLev;
	}

	public void setCmReplyLev(int cmReplyLev) {
		this.cmReplyLev = cmReplyLev;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Comments [cmNo=" + cmNo + ", userId=" + userId + ", cmContent=" + cmContent + ", cmDate=" + cmDate
				+ ", cmRef=" + cmRef + ", cmReplyRef=" + cmReplyRef + ", cmReplyLev=" + cmReplyLev + "]";
	}

	
	

}
