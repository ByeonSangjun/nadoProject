package board.model.vo;

import java.sql.Date;

public class Board implements java.io.Serializable {
	private static final long serialVersionUID = 700L;
	
	private int boardNo;		//게시판 글 번호
	private String userId;	//작성자 아이디
	private String boardTitle;	  //제목
	private java.sql.Date boardDate; 	//작성일
	private int boardReadCount;		//조회수
	private String boardContent;		//내용
	private String bFileOriname;	//오리지널 파일명
	private String bFileRename;	//리네임 파일명
	
	public Board() {}

	public Board(int boardNo, String userId, String boardTitle, Date boardDate, int boardReadCount, String boardContent,
			String bFileOriname, String bFileRename) {
		super();
		this.boardNo = boardNo;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
		this.boardContent = boardContent;
		this.bFileOriname = bFileOriname;
		this.bFileRename = bFileRename;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public java.sql.Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(java.sql.Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getbFileOriname() {
		return bFileOriname;
	}

	public void setbFileOriname(String bFileOriname) {
		this.bFileOriname = bFileOriname;
	}

	public String getbFileRename() {
		return bFileRename;
	}

	public void setbFileRename(String bFileRename) {
		this.bFileRename = bFileRename;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", userId=" + userId + ", boardTitle=" + boardTitle + ", boardDate="
				+ boardDate + ", boardReadCount=" + boardReadCount + ", boardContent=" + boardContent
				+ ", bFileOriname=" + bFileOriname + ", bFileRename=" + bFileRename + "]";
	}
	
	
}
