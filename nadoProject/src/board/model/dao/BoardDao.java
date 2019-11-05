package board.model.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.Comments;


public class BoardDao {

	// 전체목록 페이징 처리
	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select count(*) from board";

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	// 전체목록조회
	public ArrayList<Board> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from(select rownum rnum,BOARD_NO,USER_ID,BOARD_TITLE,BOARD_DATE,BOARD_READCOUNT,"
				+ "BOARD_CONTENT,BFILE_ORINAME,BFILE_RENAME from(select * from board order by board_no desc))where rnum >= ? and rnum <= ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board board = new Board();

				board.setBoardNo(rset.getInt("board_no"));
				board.setUserId(rset.getString("user_id"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardContent(rset.getString("board_content"));
				board.setbFileOriname(rset.getString("bfile_oriname"));
				board.setbFileRename(rset.getString("bfile_rename"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 조회수 증가처리
	public int updateReadCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update board set board_readcount = board_readcount + 1 where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 상세조회
	public Board selectOne(Connection conn, int boardNo) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from board where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				board = new Board();

				board.setBoardNo(rset.getInt("board_no"));
				board.setUserId(rset.getString("user_id"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardContent(rset.getString("board_content"));
				board.setbFileOriname(rset.getString("bfile_oriname"));
				board.setbFileRename(rset.getString("bfile_rename"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}

	// 검색목록 페이징처리
	public int getSearchListCount(Connection conn, String titlekeyword, String writerkeyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from (select * from board where board_title like ? and user_id like ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + titlekeyword + "%");
			pstmt.setString(2, "%" + writerkeyword + "%");

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	// 검색목록조회
	public ArrayList<Board> selectSearchList(Connection conn, int startRow, int endRow, String titlekeyword, String writerkeyword) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from(select rownum rnum, BOARD_NO, USER_ID, BOARD_TITLE, BOARD_DATE, "
				+ "BOARD_READCOUNT, BOARD_CONTENT, BFILE_ORINAME, BFILE_RENAME "
				+ "from(select * from board order by board_no desc)where board_title like ? and user_id like ?)"
				+ "where rnum >= ? and rnum <= ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + titlekeyword + "%");
			pstmt.setString(2, "%" + writerkeyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Board board = new Board();

				board.setBoardNo(rset.getInt("board_no"));
				board.setUserId(rset.getString("user_id"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardDate(rset.getDate("board_date"));
				board.setBoardReadCount(rset.getInt("board_readcount"));
				board.setBoardContent(rset.getString("board_content"));
				board.setbFileOriname(rset.getString("bfile_oriname"));
				board.setbFileRename(rset.getString("bfile_rename"));

				list.add(board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 새게시글 입력
	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into board values ((select max(board_no) + 1 from board), ?, ?, default, default, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getUserId());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getbFileOriname());
			pstmt.setString(5, board.getbFileRename());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 수정
	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "update board set board_title = ?, board_content = ?, bfile_ORINAME = ?, bfile_RENAME = ? where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getbFileOriname());
			pstmt.setString(4, board.getbFileRename());
			pstmt.setInt(5, board.getBoardNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 삭제
	public int deleteBoard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from board where board_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//댓글 등록
	public int insertComments(Connection conn, Comments comments) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into comments values (COMMENTS_SEQ.nextval, ?, ?, default, ?, COMMENTS_SEQ.currval, default)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comments.getUserId());
			pstmt.setString(2, comments.getCmContent());
			pstmt.setInt(3, comments.getCmRef());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//댓글 목록
	public ArrayList<Comments> replylist(Connection conn, int boardNo) {
		ArrayList<Comments> list = new ArrayList<Comments>();
	      PreparedStatement stmt = null;
	      ResultSet rset = null;
	      String query = "select * from COMMENTS where CM_REF = ? order by CM_REPLY_REF asc, CM_REPLY_LEV asc, CM_NO asc";
	      try {
	         stmt = conn.prepareStatement(query);
	         stmt.setInt(1, boardNo);
	         
	         rset = stmt.executeQuery();
	         while (rset.next()) {
	            Comments comments = new Comments();
	            comments.setCmNo(rset.getInt("CM_NO"));
	            comments.setUserId(rset.getString("USER_ID"));
	            comments.setCmContent(rset.getString("CM_CONTENT"));
	            comments.setCmDate(rset.getDate("CM_DATE"));
	            comments.setCmRef(rset.getInt("CM_REF"));
	            comments.setCmReplyRef(rset.getInt("CM_REPLY_REF"));
	            comments.setCmReplyLev(rset.getInt("CM_REPLY_LEV"));
	            
	            list.add(comments);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(stmt);
	      }
	      return list;
	}
	
	
	//댓글 대댓글 삭제
	public int deleteReply(Connection conn, int cmno, int cmreplylev) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from comments where ";
		
		/*if(boardLevel == 1)	//원글 삭제시 원글과 댓글 모두 삭제
			query += "board_ref = ?";
		else */if(cmreplylev == 1)	//원글에 대한 댓글 삭제시 해당 댓글과 대댓글 삭제
			query += "CM_REPLY_REF = ?";
		else if(cmreplylev == 2)	//댓글의 댓글 삭제시 해당 대댓글만 삭제
			query += "CM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cmno);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//댓글 수정
	public int updateReply(Connection conn, int cmno, String cmcomtent) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update comments set CM_CONTENT = ?, CM_DATE = default where CM_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cmcomtent);
			pstmt.setInt(2, cmno);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//대댓글 등록
	public int insertComments2(Connection conn, Comments comments) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "insert into comments values (COMMENTS_SEQ.nextval, ?, ?, default, ?, ?, 2)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comments.getUserId());
			pstmt.setString(2, comments.getCmContent());
			pstmt.setInt(3, comments.getCmRef());
			pstmt.setInt(4, comments.getCmReplyRef());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//게시글 삭제시 게시글안의 댓글 삭제
	public int deleteBoardReply(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = "delete from COMMENTS where CM_REF = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	

}
