package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class NoticeDao {

	
	//전체목록 페이징 처리
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from notice";
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	
	
	//전체목록조회
	public ArrayList<Notice> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from(select rownum rnum,NOTICE_NO, USER_ID, NOTICE_TITLE, NOTICE_DATE, NOTICE_READCOUNT, NOTICE_CONTENT, NFILE_ORINAME, NFILE_RENAME " + 
				"from(select * from notice order by notice_no desc))where rnum >= ? and rnum <= ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setUserId(rset.getString("user_id"));
				notice.setNoticeTitle(rset.getNString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_readcount"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNfileOriname(rset.getString("nfile_oriname"));
				notice.setNfileRename(rset.getString("nfile_rename"));
				
				list.add(notice);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	//조회수 증가처리
	public int updateReadCount(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice set notice_readcount = notice_readcount + 1 where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//상세조회
	public Notice selectOne(Connection conn, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setUserId(rset.getString("user_id"));
				notice.setNoticeTitle(rset.getNString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_readcount"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNfileOriname(rset.getString("nfile_oriname"));
				notice.setNfileRename(rset.getString("nfile_rename"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return notice;
	}
	
	
	//검색목록 페이징처리
	public int getSearchListCount(Connection conn, String titlekeyword, String writerkeyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select count(*) from (select * from notice where notice_title like ? and user_id like ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + titlekeyword + "%");
			pstmt.setString(2, "%" + writerkeyword + "%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	
	//검색목록조회
	public ArrayList<Notice> selectSearchList(Connection conn, int startRow, int endRow, String titlekeyword, String writerkeyword) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from("
	+ "select rownum rnum, NOTICE_NO,USER_ID, NOTICE_TITLE, NOTICE_DATE, NOTICE_READCOUNT, NOTICE_CONTENT, NFILE_ORINAME, NFILE_RENAME "
						+ "from(select * from notice order by notice_no desc)where notice_title like ? and user_id like ? )where rnum >= ? and rnum <= ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + titlekeyword + "%");
			pstmt.setString(2, "%" + writerkeyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setUserId(rset.getString("user_id"));
				notice.setNoticeTitle(rset.getNString("notice_title"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setNoticeReadCount(rset.getInt("notice_readcount"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNfileOriname(rset.getString("nfile_oriname"));
				notice.setNfileRename(rset.getString("nfile_rename"));
				
				list.add(notice);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	//새공지글 입력
	public int insertNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into notice values ((select max(notice_no) + 1 from notice), ?, ?, default, default, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getUserId());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getNfileOriname());
			pstmt.setString(5, notice.getNfileRename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//공지글 수정
	public int updateNotice(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice set notice_title = ?, notice_content = ?, NFILE_ORINAME = ?, NFILE_RENAME = ? where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getNfileOriname());
			pstmt.setString(4, notice.getNfileRename());
			pstmt.setInt(5, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//공지글 삭제
	public int deleteNotice(Connection conn, int noticeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query ="delete from notice where notice_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	



	

	
	
	
	
	
	
	
	
}
