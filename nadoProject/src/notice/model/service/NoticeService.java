package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

import static common.JDBCTemplate.*;

public class NoticeService {
	private NoticeDao ndao = new NoticeDao();
	
	public NoticeService() {}
	
	//전체목록 페이징 처리
		public int getListCount(Object comp) {
			Connection conn = getConnection(comp);
			int listCount = ndao.getListCount(conn);
			close(conn);
			return listCount;
		}
	
	//전체목록조회
	public ArrayList<Notice> selectList(Object comp, int startRow, int endRow) {
		Connection conn = getConnection(comp);
		ArrayList<Notice> list = ndao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}
	
	//상세조회 조회수 증가처리
		public void updateReadCount(Object comp, int noticeNo) {
			Connection conn = getConnection(comp);
			int result = ndao.updateReadCount(conn, noticeNo);
			if(result > 0)
				commit(conn);
			else
				rollback(conn);
			close(conn);
		}
		
	//상세조회
	public Notice selectOne(Object comp, int noticeNo) {
		Connection conn = getConnection(comp);
		Notice notice = ndao.selectOne(conn, noticeNo);
		close(conn);
		return notice;
	}
	
	//검색목록 페이징처리
	public int getSearchListCount(Object comp, String titlekeyword, String writerkeyword) {
		Connection conn = getConnection(comp);
		int listCount = ndao.getSearchListCount(conn, titlekeyword, writerkeyword);
		close(conn);
		return listCount;
	}
	
	//검색목록조회
	public ArrayList<Notice> selectSearchList(Object comp, int startRow, int endRow, String titlekeyword, String writerkeyword) {
		Connection conn = getConnection(comp);
		ArrayList<Notice> list = ndao.selectSearchList(conn, startRow, endRow, titlekeyword, writerkeyword);
		close(conn);
		return list;
	}
	
	//새공지글 입력
	public int insertNotice(Object comp, Notice notice) {
		Connection conn = getConnection(comp);
		int result = ndao.insertNotice(conn, notice);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//공지글 수정
	public int updateNotice(Object comp, Notice notice) {
		Connection conn = getConnection(comp);
		int result = ndao.updateNotice(conn, notice);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//공지글 삭제
	public int deleteNotice(Object comp, int noticeNo) {
		Connection conn = getConnection(comp);
		int result = ndao.deleteNotice(conn, noticeNo);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;	
	}
	
	

	

	
	

	

}
