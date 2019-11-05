package chatting.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import chatting.model.dao.ChattingDao;
import chatting.model.vo.Chatting;
import employee.model.vo.Company;

public class ChattingService {
	private ChattingDao chDao = new ChattingDao();
	public ChattingService() {}
	
	
	// 사번으로 내가 참여한 방정보들 조회
	public ArrayList<Chatting> myRoomList(Object comp, String empId) {
		Connection conn = getConnection(comp);
		ArrayList<Chatting> chatList = chDao.myRoomList(conn, empId);
		close(conn);
		return chatList;
	}
	
	// 참여방에 마지막 글
	public ArrayList<Chatting> searchLastContents(Object comp, String empId) {
		Connection conn = getConnection(comp);
		ArrayList<Chatting> lastContents = chDao.searchLastContents(conn, empId);
		close(conn);
		return lastContents;
	}
	
	// 1. 사번 두개 가지고 와서 방 있는지 확인하는 메소드
	public Chatting searchThisRoom(Object comp, String myId, String frId) {
		Connection conn = getConnection(comp);
		Chatting chat = chDao.searchThisRoom(conn, myId, frId);
		close(conn);
		return chat;
	}

	// 2. 1번에서 방 있는지 확인했으면 그 방번호 가지고 와서 전체 글 조회
	public ArrayList<Chatting> selectAllContent(Object comp, int chatRoomNo) {
		Connection conn = getConnection(comp);
		ArrayList<Chatting> list = chDao.selectAllContent(conn, chatRoomNo);
		close(conn);
		return list;
	}

	// 같은방에 인원들 사번
	public String roomUsers(Object comp, int chatRoomNo) {
		Connection conn = getConnection(comp);
		String roomUsers = chDao.roomUsers(conn, chatRoomNo);
		close(conn);
		return roomUsers;
	}
	
	// 방만들기
	public int createRoom(Object comp, String myId, String frId, String myName, String frName) {
		Connection conn = getConnection(comp);
		int result = chDao.createRoom(conn, myId);
		int lastRoomNo = 0;
		int count = 0;
		if (result > 0) {
			lastRoomNo = chDao.lastRoomNo(conn, myId);
			if (lastRoomNo > 0) {		
				count += chDao.insertInTheChat(conn, lastRoomNo, myId, frName);
				count += chDao.insertInTheChat(conn, lastRoomNo, frId, myName);		
				if (count > 1)
					commit(conn);
				else
					rollback(conn);
			}
		} else
			rollback(conn);
		close(conn);
		return lastRoomNo;
	}
////	여기까지 개인톡
//
//	//여기부터 리스트(단톡)
//	
//	public Chatting searchThisRoom2(Object comp, int roomNo, String empId) {
//		Connection conn = getConnection(comp);
//		Chatting chat = chDao.searchThisRoom2(conn, roomNo, empId);
//		close(conn);
//		return chat;
//	}	
//
//
//대화저장
	
	public void insertContent(Object comp, String content) {
		Connection conn = getConnection(comp);
		int result = chDao.insertContent(conn, content);
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	//서버에서 방만들때 상대방 이름 넣어주기 위해 쓰는 이름 조회 메소드
	public ArrayList<Chatting> searchName(Company comp, String myId, String frId) {
		Connection conn = getConnection(comp);
		ArrayList<Chatting> names = chDao.searchName(conn, myId, frId);
		close(conn);
		return names;
	}


	public String searchMyRoomName(Company comp, String myId, String roomNo) {
		Connection conn = getConnection(comp);
		String roomName = chDao.searchMyRoomName(conn, myId, roomNo);
		close(conn);
		return roomName;
	}

	//그룹방 만들기
	public int createGroupRoom(Company comp, String myId, String ids, String title) {
		Connection conn = getConnection(comp);
		int result = chDao.createGroupRoom(conn, myId);
		int lastRoomNo = 0;
		int count = 0;
		if (result > 0) {
			lastRoomNo = chDao.lastRoomNo(conn, myId);
			if (lastRoomNo > 0) {
				String[] empIds = ids.split("ł");
					count += chDao.insertInTheChat(conn, lastRoomNo, myId, title);
				for(int i = 0; empIds.length > i ; i++) {
					count += chDao.insertInTheChat(conn, lastRoomNo, empIds[i], title);
				}	
				if (count > empIds.length)
					commit(conn);
				else
					rollback(conn);
			}
		} else
			rollback(conn);
		close(conn);
		return lastRoomNo;	
	}


	public String searchRoomInfo(Object comp, String empId, String chatRoomNo) {
		Connection conn = getConnection(comp);
		String chatTitle = chDao.searchRoomInfo(conn, empId, chatRoomNo);
		close(conn);
		
		return chatTitle;
	}

	//단톡 개인톡 확인
	public String checkGroupRoom(Company comp, int roomNo) {
		Connection conn = getConnection(comp);
		String checkRoom = chDao.checkGroupRoom(conn, roomNo);
		close(conn);
		return checkRoom;
	}

	//단톡으로 바꿔줌
	public int updateRoomInfo(Company comp, int roomNo) {
		Connection conn =getConnection(comp);
		int result = chDao.updateRoomInfo(conn, roomNo);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}


	public String searchName2(Company comp, String empId) {
		Connection conn = getConnection(comp);
		String name = chDao.searchName2(conn, empId);
		close(conn);
		return name;
	}


	public void updateInthechat(Company comp, int roomNo, String empId, String plusName) {
		Connection conn = getConnection(comp);
		int result = chDao.updateInthechat(conn, roomNo, empId, plusName);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);	
	}


	public void insertInthechat(Company comp, int roomNo, String empId, String plusName) {
		Connection conn = getConnection(comp);
		int result = chDao.insertInthechat(conn, roomNo, empId, plusName);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);		
	}
}
