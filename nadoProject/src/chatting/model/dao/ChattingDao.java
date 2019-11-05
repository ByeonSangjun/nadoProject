package chatting.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import chatting.model.vo.Chatting;

public class ChattingDao {
	public ChattingDao() {
	}

	// 내가 참여한 방정보들
	public ArrayList<Chatting> myRoomList(Connection conn, String empId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Chatting> chatList = new ArrayList<Chatting>();
		String query = "select * from inthechat where emp_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chatting chat = new Chatting();

				chat.setChatRoomNo(rset.getInt("chat_room_no"));
				chat.setEmpId(rset.getString("emp_id"));
				chat.setChatTitle(rset.getString("chat_title"));

				chatList.add(chat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return chatList;
	}

	// 참여방에 마지막 글들
	public ArrayList<Chatting> searchLastContents(Connection conn, String empId) {
		ArrayList<Chatting> lastContents = new ArrayList<Chatting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select chat_room_no, chat_content from chcontent left join inthechat using (chat_room_no, emp_id) "
				+ "where (chat_room_no, chat_no) in (select chat_room_no, max(chat_no) "
				+ "from chcontent where chat_room_no in (select chat_room_no from inthechat where emp_id= ?) "
				+ "group by chat_room_no)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chatting chat = new Chatting();
				chat.setChatRoomNo(rset.getInt("chat_room_no"));
				chat.setChatContent(rset.getString("chat_content"));

				lastContents.add(chat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return lastContents;
	}
	
	public Chatting searchThisRoom(Connection conn, String myId, String frId) {
		Chatting chat = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select chat_room_no from chatting where group_room = 'N' " + 
				"intersect (select chat_room_no from inthechat where emp_id = ? intersect " + 
				"select chat_room_no from inthechat where emp_id = ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myId);
			pstmt.setString(2, frId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				chat = new Chatting();
				chat.setChatRoomNo(rset.getInt("chat_room_no"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return chat;
	}
	// 2. 1번에서 방 있는지 확인했으면 그 방번호 가지고 와서 전체 글 조회
	public ArrayList<Chatting> selectAllContent(Connection conn, int chatRoomNo) {
		ArrayList<Chatting> chatContent = new ArrayList<Chatting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from chcontent where chat_room_no = ? order by chat_no asc";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chatRoomNo);	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Chatting chat = new Chatting();
				
				chat.setChatNo(rset.getInt("chat_no"));
				chat.setChatRoomNo(rset.getInt("chat_room_no"));
				chat.setEmpId(rset.getString("emp_id"));
				chat.setChatContent(rset.getString("chat_content"));
				chat.setChatSendTime(rset.getDate("chat_send_time"));
				
				chatContent.add(chat);		
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return chatContent;
	}
	
	public String roomUsers(Connection conn, int chatRoomNo) {
		String roomUsers = ""; // 여기다 담아서 보냄
		ArrayList<Chatting> chatList = new ArrayList<Chatting>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from inthechat where chat_room_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chatRoomNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Chatting chat = new Chatting();
				chat.setChatRoomNo(rset.getInt("chat_room_no"));
				chat.setEmpId(rset.getString("emp_id"));

				chatList.add(chat);
			}

			for (int i = 0; chatList.size() > i; i++) {
				for (int j = i + 1; chatList.size() > j; j++) {
					if (chatList.get(i).getChatRoomNo() == chatList.get(j).getChatRoomNo()) {
						chatList.get(i).setEmpId(chatList.get(i).getEmpId() + "ł" + chatList.get(j).getEmpId());
						chatList.remove(j);
						--j;
					}
				}
			}
			roomUsers = chatList.get(0).getChatRoomNo() + "ł" + chatList.get(0).getEmpId();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return roomUsers;
	}
	
	//방만들기
	public int createRoom(Connection conn, String myId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into chatting values(chat_seq.nextval, ?, default)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,myId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
//	//마지막 방번호
	public int lastRoomNo(Connection conn, String myId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query= "select max(chat_room_no) from chatting where emp_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	// inthechat에 만들어주기
	public int insertInTheChat(Connection conn, int lastRoomNo, String empId, String title) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into inthechat values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, lastRoomNo);
			pstmt.setString(2, empId);
			pstmt.setString(3, title);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
//	
//	//여기까지 개인톡방에 관한 메소드
//	
//	//여기부턴 리스트에 관한 메소드
//	public Chatting searchThisRoom2(Connection conn, int roomNo, String empId) {
//		Chatting chat = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "select * from inthechat where emp_id= ? and chat_room_no = ?";
//
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, empId);
//			pstmt.setInt(2, roomNo);
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				chat = new Chatting();
//				chat.setChatRoomNo(rset.getInt(1));
//				chat.setEmpId(rset.getString("emp_id"));
//				chat.setChatStaticTitle(rset.getString("chat_static_title"));
//				chat.setChatDynamicTitle(rset.getString("chat_dynamic_title"));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return chat;
//	}
//	
//대화저장
	public int insertContent(Connection conn, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		//방번호, 사번, 이름, 내용
		String[] total = content.split("ł");
		int chatRoomNo = Integer.parseInt(total[0]);
		String empId = total[1];
		String empName = total[2];
		String cont = total[3];
		
		String query="insert into chcontent values(chcon_seq.nextval, ?, ?, ?, sysdate)";  //나중에 날짜도 넣어줘야됨
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, chatRoomNo);
			pstmt.setString(2, empId);
			pstmt.setString(3, cont);	
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	//서버에서 방만들때 상대방 이름 넣어주기 위해 쓰는 이름 조회 메소드
	public ArrayList<Chatting> searchName(Connection conn, String myId, String frId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Chatting> list = new ArrayList<Chatting>();
		String query = "select emp_id, emp_name from employee where emp_id = ? or emp_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myId);
			pstmt.setString(2, frId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Chatting chat = new Chatting();
				chat.setEmpId(rset.getString("emp_id"));
				chat.setEmpName(rset.getString("emp_name"));
				
				list.add(chat);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	//여긴 실패
//	//2019-10-25 모든 메소드 다시 만든다!
//	// 1. 사번 두개 가지고 와서 방 있는지 확인하는 메소드

	public String searchMyRoomName(Connection conn, String myId, String roomNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select chat_title from inthechat where emp_id = ? and chat_room_no = ?";
		int chatRoomNo = Integer.valueOf(roomNo);
		String roomName = "";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, myId);
			pstmt.setInt(2, chatRoomNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				roomName = rset.getString("chat_title");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return roomName;
	}

	public int createGroupRoom(Connection conn, String myId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into chatting values(chat_seq.nextval, ?, 'Y')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,myId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String searchRoomInfo(Connection conn, String empId, String chatRoomNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String chatTitle = "";
		int roomNo = Integer.parseInt(chatRoomNo);
		String query = "select * from inthechat where emp_id=? and chat_room_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,empId);
			pstmt.setInt(2, roomNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				chatTitle = rset.getString("chat_title");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return chatTitle;
	}

	public String checkGroupRoom(Connection conn, int roomNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select group_room from chatting where chat_room_no = ?";
		String groupRoom = "";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				groupRoom = rset.getString("group_room");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return groupRoom;
	}

	public int updateRoomInfo(Connection conn, int roomNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update chatting set group_room = 'Y' where chat_room_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String searchName2(Connection conn, String empId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String name = "";
		String query = "select emp_name from employee where emp_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				name = rset.getString("emp_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return name;
	}

	public int updateInthechat(Connection conn, int roomNo, String empId, String plusName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update inthechat set chat_title = ? where emp_id = ? and chat_room_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, plusName);
			pstmt.setString(2, empId);
			pstmt.setInt(3, roomNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertInthechat(Connection conn, int roomNo, String empId, String plusName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into inthechat values(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2, empId);
			pstmt.setString(3, plusName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

//	public String checkGroupRoom(Connection conn, String roomNo) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "select group_room from chatting where chat_room_no = ?";
//		int chatRoomNo = Integer.valueOf(roomNo);
//		String groupRoom = "";
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, chatRoomNo);
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				groupRoom = rset.getString("group_room");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return groupRoom;
//	}
	
	//수정중
//	public Chatting searchThisRoom(Connection conn, String empIds) {
//		Chatting chat = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "select chat_room_no from inthechat group by chat_room_no having count(emp_id) <= 2 " + 
//				"intersect " + 
//				"(select chat_room_no from inthechat where emp_id = ? intersect " + 
//				"select chat_room_no from inthechat where emp_id = ?)";
//		
//		String[] id = empIds.split(",");
//		System.out.println(empIds);
//		String myId = id[0];
//		System.out.println(myId);
//		String frId = id[1];
//		System.out.println(frId);
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, myId);
//			pstmt.setString(2, frId);
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				chat = new Chatting();
//				chat.setChatRoomNo(rset.getInt(1));		
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return chat;
//	}


}
