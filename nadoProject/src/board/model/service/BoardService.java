package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.Comments;

import static common.JDBCTemplate.*;

public class BoardService {
	private BoardDao bdao = new BoardDao();

	public BoardService() {}

	// 전체목록 페이징 처리
	public int getListCount(Object comp) {
		Connection conn = getConnection(comp);
		int listCount = bdao.getListCount(conn);
		close(conn);
		return listCount;
	}

	// 전체목록조회
	public ArrayList<Board> selectList(Object comp, int startRow, int endRow) {
		Connection conn = getConnection(comp);
		ArrayList<Board> list = bdao.selectList(conn, startRow, endRow);
		close(conn);
		return list;
	}

	// 상세조회 조회수 증가처리
	public int updateReadCount(Object comp, int boardNo) {
		Connection conn = getConnection(comp);
		int result = bdao.updateReadCount(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 상세조회
	public Board selectOne(Object comp, int boardNo) {
		Connection conn = getConnection(comp);
		Board board = bdao.selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	// 검색목록 페이징처리
	public int getSearchListCount(Object comp, String titlekeyword, String writerkeyword) {
		Connection conn = getConnection(comp);
		int listCount = bdao.getSearchListCount(conn, titlekeyword, writerkeyword);
		close(conn);
		return listCount;
	}

	// 검색목록조회
	public ArrayList<Board> selectSearchList(Object comp, int startRow, int endRow, String titlekeyword, String writerkeyword) {
		Connection conn = getConnection(comp);
		ArrayList<Board> list = bdao.selectSearchList(conn, startRow, endRow, titlekeyword, writerkeyword);
		close(conn);
		return list;
	}

	// 새게시판 입력
	public int insertBoard(Object comp, Board board) {
		Connection conn = getConnection(comp);
		int result = bdao.insertBoard(conn, board);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 게시글 수정
	public int updateBoard(Object comp, Board board) {
		Connection conn = getConnection(comp);
		int result = bdao.updateBoard(conn, board);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(Object comp, int boardNo) {
		Connection conn = getConnection(comp);
		int result = bdao.deleteBoard(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//댓글 등록
	public int insertComments(Object comp, Comments comments) {
		Connection conn = getConnection(comp);
		int result = bdao.insertComments(conn, comments);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//댓글 목록
	public ArrayList<Comments> replylist(Object comp, int boardNo) {
		Connection conn = getConnection(comp);
		ArrayList<Comments> list = bdao.replylist(conn, boardNo);
		close(conn);
		return list;
	}
	
	//댓글 대댓글 삭제
	public int deleteReply(Object comp, int cmno, int cmreplylev) {
		Connection conn = getConnection(comp);
		int result = bdao.deleteReply(conn, cmno, cmreplylev);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//댓글 수정
	public int updateReply(Object comp, int cmno, String cmcomtent) {
		Connection conn = getConnection(comp);
		int result = bdao.updateReply(conn, cmno, cmcomtent);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//대댓글 등록
	public int insertComments2(Object comp, Comments comments) {
		Connection conn = getConnection(comp);
		int result = bdao.insertComments2(conn, comments);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	//게시글 삭제시 게시글안의 댓글 삭제
	public int deleteBoardReply(Object comp, int boardNo) {
		Connection conn = getConnection(comp);
		int result = bdao.deleteBoardReply(conn, boardNo);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	

}
