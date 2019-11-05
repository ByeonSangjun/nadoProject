package chatting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import chatting.model.service.ChattingService;
import chatting.model.vo.Chatting;
import employee.model.vo.Company;

@ServerEndpoint("/chatserver")
public class ChattingMainServer {
	private ChattingService cServ = new ChattingService();

	// 웹소켓 접속자 사번저장용 (키 : Session, 값 : empId)
	private static Map<Session, String> empIdSession = Collections.synchronizedMap(new HashMap<Session, String>());

	// 채팅방 저장용 (키 chat_room_no, 값 : users) 
	private static Map<Session, String> roomList = Collections.synchronizedMap(new HashMap<Session, String>());

	// 회사 저장용 (키 Session, Company)
	private static Map<Session, Company> cp = new HashMap<Session, Company>();

	@OnOpen
	public void onOpen(Session session) {
	}

	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		// 받은 메시지 "ł"로 잘라서 분석
		String content = msg;
		String cHead = content.substring(0, content.indexOf("ł"));
		String cBody = content.substring(content.indexOf("ł") + 1, content.length());

		if (cHead.equals("empId")) {		
			// 접속시에 empId 보내서 맵에 사번이랑 새션같이 저장함
			empIdSession.put(session, cBody);
			System.out.println("첫접속시에 보낸 사번" + empIdSession);
			
		}else if (cHead.equals("comp")) {
			// 접속시에 회사 정보 보내서 맵에 저장
			String[] company = cBody.split("ł");
			Company comp = new Company();
			comp.setDriver(company[0]);
			comp.setUrl(company[1]);
			comp.setId(company[2]);
			comp.setPassword(company[3]);
			cp.put(session, comp);
			System.out.println("첫접속시에 보낸 회사정보" + cp);
			
		}else if(cHead.equals("searchRoom")){ //사번 두개 가지고 가서 2명 이하인 방 있는지 확인
			String[] fBody = cBody.split("ł");
			String myId = fBody[0];
			String frId = fBody[1];
			Chatting chat = cServ.searchThisRoom(cp.get(session), myId, frId);
			if(chat != null) { //조회 성공했으면
				String roomNo = String.valueOf(chat.getChatRoomNo());
				session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기	
			}else { //조회 실패했으면 방 만들어줌
				ArrayList<Chatting> names = cServ.searchName(cp.get(session), myId, frId);  //상대방 이름 조회
				String myName = "";
				String frName = "";
				for(Chatting clist : names) {
					if(clist.getEmpId().equals(myId))
						myName = clist.getEmpName();
					else
						frName = clist.getEmpName();
				}
				String roomNo = String.valueOf(cServ.createRoom(cp.get(session), myId, frId, myName, frName));
				session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기
			}
		}else if(cHead.equals("searchListRoom")){
			String[] fBody = cBody.split("ł");
			String roomNo = fBody[0];
			session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기		
		}else if (cHead.equals("rooms")) {
			// 내가 참여중인 방들 정보 저장시키기
			synchronized (roomList) {
				for(Session mapkey : roomList.keySet()) {  //먼저 기본방 다 조사해서 같은방 있는지 확인
					String rUsers = roomList.get(mapkey);  //기존방에 방번호와 유저들
					String roomN = rUsers.substring(0, rUsers.indexOf("ł"));  //기존방 방번호
					if(roomN.equals(cBody.substring(0, cBody.indexOf("ł"))))  //기존방번호랑 지금 가지고온 방번호랑 같으면
						roomList.put(mapkey, cBody);			
				}
				roomList.put(session, cBody);				
			}
		}else if (cHead.equals("rChat")) {
			String roomNo = cBody.substring(0, cBody.indexOf("ł")); // 받은방번호(보낼방번호)
			String rcontent = cBody.substring(cBody.indexOf("ł") + 1, cBody.length()); //받은내용(보낼내용 사번+"ł"+이름+"ł"+내용)
			
			//단톡인지 개인톡인지 확인
//			String groupRoom = cServ.checkGroupRoom(cp.get(session), roomNo);  //이거 필요없음 나중에 지움
//			
//			String myId = rcontent.substring(0, rcontent.indexOf("ł"));  //보낸사람 사번  //이것도 필요없음 나중에 지움
			
			synchronized (roomList) {  //방을 열고 있는 유저한테 보내는 for
				for (Session mapkey : roomList.keySet()) {
					String room = roomList.get(mapkey); // 방번호+"ł"+유저들 들어있음
					String no = room.substring(0, room.indexOf("ł")); // 기존에 있는 방번호
					if(mapkey != session) {
						if (no.equals(roomNo)) {
							mapkey.getBasicRemote().sendText(rcontent);  //방을 열고 있는 유저한테 보냄
						}
					}
				}
				String room = roomList.get(session); // 채팅 리스트 쪽에 보내는 for
				synchronized (empIdSession) {
					for (Session mapkey : empIdSession.keySet()) { // 사번저장된거 키셋으로 돌림
						jun: for (String list : room.substring(room.indexOf("ł") + 1, room.length()).split("ł")) { // 저장되있는사번들
							if (empIdSession.get(mapkey).equals(list)) { // 값이 같으면 세션으로 내용 보냄
								String chatTitle = cServ.searchMyRoomName(cp.get(session), list, roomNo);  //각자 타이틀 보내줌
								mapkey.getBasicRemote().sendText("sendToMainł"+cBody+"ł"+chatTitle);
								break jun;
							}
						}
					}
				} // 동기화
			} //동기화 	
			new ChattingService().insertContent(cp.get(session), cBody); //db에 대화내용 저장
		}else if(cHead.equals("createChat")) {
			String myId = cBody.substring(0, cBody.indexOf("ł"));
			String titleAndUsers = cBody.substring(cBody.indexOf("ł")+1, cBody.length());
			String title = titleAndUsers.substring(0, titleAndUsers.indexOf("ł"));
			String ids = titleAndUsers.substring(titleAndUsers.indexOf("ł")+1, titleAndUsers.length());
		
			if(ids.indexOf("ł") == -1) {
				Chatting chat = cServ.searchThisRoom(cp.get(session), myId, ids);
				if(chat != null) { //조회 성공했으면
					String roomNo = String.valueOf(chat.getChatRoomNo());
					session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기	
				}else { //조회 실패했으면 방 만들어줌
					ArrayList<Chatting> names = cServ.searchName(cp.get(session), myId, ids);  //상대방 이름 조회
					String myName = "";
					String frName = "";
					for(Chatting clist : names) {
						if(clist.getEmpId().equals(myId))
							myName = clist.getEmpName();
						else
							frName = clist.getEmpName();
					}
					String roomNo = String.valueOf(cServ.createRoom(cp.get(session), myId, ids, myName, frName));
					session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기
				}
			}else {
				String roomNo = String.valueOf(cServ.createGroupRoom(cp.get(session), myId, ids, title));
				session.getBasicRemote().sendText("openRoomł"+roomNo); //방번호 보내기
			}
		}else if(cHead.equals("inviteChat")) { //기존방에서 초대
			
			int roomNo = Integer.parseInt(cBody.substring(0, cBody.indexOf("ł")));  //방번호
			String roomNo2 = String.valueOf(roomNo);
			String invited = cBody.substring(cBody.indexOf(":")+2, cBody.length());  //초대된 사람들
			String updateRoomList = cBody.replace(":", "");   //서버 저장용
			String inRoom = cBody.substring(cBody.indexOf("ł")+1, cBody.indexOf(":"));//기존방 사람들
			
			String names = updateRoomList.substring(updateRoomList.indexOf("ł")+1, updateRoomList.length());  //이름 조회용
			String[] names2 = names.split("ł");
			
			String plusName = "";  //방제목으로 쓸 이름 합치기
			//이름들 조회
			for(int i = 0 ; names2.length>i; i++) {
				if(names2.length-1 == i) {
					plusName += cServ.searchName2(cp.get(session), names2[i]);
				}else {
					plusName += cServ.searchName2(cp.get(session), names2[i]) + ", "; 
				}		
			}
			
			//오류조회용 
//			System.out.println("방 이름 조회 : " + plusName);
//			System.out.println("cBody : " + cBody);
//			System.out.println("이름 조회용 : "+names);		
//			System.out.println("기존방인원 : "+inRoom);
//			System.out.println("서버저장용 : " + updateRoomList);
//			System.out.println("초대된 사람 사번 : "+invited);
			
			//개인톡인지 단톡인지 확인
			String gRoom = cServ.checkGroupRoom(cp.get(session), roomNo);
			
			if(gRoom.equals("N")) { //개인톡이면
				int result = cServ.updateRoomInfo(cp.get(session), roomNo); //개인톡이면 단톡으로 바꿔줌
				if(result > 0) {
					//기존입장중인 사람은 update
					String[] inRoom2 = inRoom.split("ł");
					for (int i = 0; inRoom2.length > i; i++) {
						cServ.updateInthechat(cp.get(session), roomNo, inRoom2[i], plusName);
					}
					//새로 초대된 사람은 insert
					String[] invited2 = invited.split("ł");
					for(int i = 0; invited2.length > i; i++) {
						cServ.insertInthechat(cp.get(session), roomNo, invited2[i], plusName);
					}
					
					//roomList에 같은방 가진 map 최신화 해줌
					
					synchronized (roomList) {
						for(Session mapkey : roomList.keySet()) {  //먼저 기본방 다 조사해서 같은방 있는지 확인
							String rUsers = roomList.get(mapkey);  //기존방에 방번호와 유저들
							String roomN = rUsers.substring(0, rUsers.indexOf("ł"));  //기존방 방번호
							if(roomN.equals(roomNo2))  //기존방번호랑 지금 가지고온 방번호랑 같으면
								roomList.put(mapkey, updateRoomList);					
						}							
					}			
				}else {
					System.out.println("단톡방으로 바꾸기 실패");
				}
			}else {
				//단톡이면
				//기존입장중인 사람은 update
				String[] inRoom2 = inRoom.split("ł");
				for (int i = 0; inRoom2.length > i; i++) {
					cServ.updateInthechat(cp.get(session), roomNo, inRoom2[i], plusName);
				}
				//새로 초대된 사람은 insert
				String[] invited2 = invited.split("ł");
				for(int i = 0; invited2.length > i; i++) {
					cServ.insertInthechat(cp.get(session), roomNo, invited2[i], plusName);
				}
				
				//roomList에 같은방 가진 map 최신화 해줌	
				synchronized (roomList) {
					for(Session mapkey : roomList.keySet()) {  //먼저 기본방 다 조사해서 같은방 있는지 확인
						String rUsers = roomList.get(mapkey);  //기존방에 방번호와 유저들
						String roomN = rUsers.substring(0, rUsers.indexOf("ł"));  //기존방 방번호
						if(roomN.equals(cBody.substring(0, cBody.indexOf("ł"))))  //기존방번호랑 지금 가지고온 방번호랑 같으면
							roomList.put(mapkey, updateRoomList);
					}		
				}			
			}		
			//다 되면 메세지 보내서 openChat 에 roomUsers 바꿔줘야함
			synchronized (roomList) {
				for (Session mapkey : roomList.keySet()) {
					String room = roomList.get(mapkey); // 방번호+"ł"+유저들 들어있음
					String no = room.substring(0, room.indexOf("ł")); // 기존에 있는 방번호
					if (no.equals(roomNo2)) {
						mapkey.getBasicRemote().sendText("inviteUserł"+updateRoomList+":"+plusName); // 방을 열고 있는 유저한테 보냄
					}
				}
			} // 동기화
		}else if(cHead.equals("etr")){ //방나가기
			//서버에서 삭제 (roomList)
			String[] usersAndMyId =cBody.split(":");   //방번호유저들 이랑 사번이랑 나눔
			String rUsers = usersAndMyId[0];		//방번호 유저들
			String rNo = rUsers.substring(0, rUsers.indexOf("ł"));    // 방번호
			String myId = usersAndMyId[1];		//내 사번
			
			//재 조합 작업 (맨 처음엔 방번호 들어있음)  
			String[] rUsers2 = rUsers.split("ł");
			String rename = "";   //새로운 방 roomList 이름
			
			if(rUsers2.length == 2) {//방에 혼자있음
				//inthechat에서 삭제해줘야함
				
			}else {//방에 나말고 다른애들 있음
				if(rUsers2[rUsers2.length-1].equals(myId)) {
					//마지막방에 내 사번이 있으면
					for(int i = 0; rUsers2.length-1 > i ; i++) {
						if(rUsers2.length-2 == i) {
							rename += rUsers2[i];
						}else {
							rename += rUsers2[i]+"ł";
						}
					}
				}else {
					//내 사번이 중간에 껴있으면
					for(int i = 0; rUsers2.length > i ; i++) {
						if(rUsers2[i].equals(myId)) {//내 사번이랑 같다면
							continue;
						}else {
							if(rUsers2.length-1 == i) {
								rename += rUsers2[i];
							}else {
								rename += rUsers2[i]+"ł";
							}
						}	
					}
				}
			}
			
			
			
			
			//db에서 삭제
			//룸 리스트에서 삭제 처리
		}
	}

	@OnError
	public void onError(Throwable error) {
		// 메세지 전송과정에서 에러가 발생한 경우 자동 실행됨
		error.printStackTrace();
	}

	@OnClose
	public void onClose(Session session) {
		empIdSession.remove(session);
		roomList.remove(session);
		cp.remove(session);
		System.out.println("제거완료");
	}
}