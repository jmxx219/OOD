import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @file ChatRoom.java
 * @author 2019136072 손지민
 * 관찰자 패턴: subject
 * 사용자 목록과 채팅 메시지 목록 유지
 * 채팅룸 목록, 사용자 목록 유지
 * 
 * 1. 해당 관찰자 패턴은 push 방법으로 구현한다. 채팅방(관찰대상)의 updateUsers(notifyObservers) 메소드에서 
 * 	  ChatMessage 정보를 유저에게 제공하고, 유저(관찰자)는 update() 메소드로 정보를 전달받는다.
 * 2. 관찰자는 관찰대상을 roomName으로 구분하고 있다. 유저는 roomName을 이용하여 여러 개의 채팅방을 구분하여 관찰하고 있다.
 * 3. UserChatWindow(observer)와 User(subject)로 채팅창에 대해서 두 객체를 관찰자와 관찰대상으로 모델링하고 있다.
 */
public class ChatRoom{
	private String roomName;
	private List<ChatMessage> roomLog = new ArrayList<>();
	// Map<사용자ID, 마지막 받은 메시지 색인>
	// 관찰자 목록
	private Map<String, Integer> userList = new HashMap<>();
	
	public ChatRoom(String name) {
		roomName = Objects.requireNonNull(name);
	}
	public String getRoomName() {
		return roomName;
	}
	
	// 채팅 서버가 채팅방에 새 메시지를 전달할 때 사용하는 메소드
	public void newMessage(ChatMessage message, ChatServer chatServer) {
		// 예외 처리를 추가하면
		roomLog.add(Objects.requireNonNull(message));
		updateUsers(chatServer);
	}
	// 관찰자 추가: 사용자의 가입
	// 사용자는 가입된 이후 발생한 메시지만 받음
	// userList Map에 사용자를 추가해야 함
	// @return 추가에 실패할 경우 false, 성공하면 true
	public boolean addUser(String userID) {
		if(!userList.containsKey(Objects.requireNonNull(userID))) {
			userList.put(userID, roomLog.size() - 1);
			return true;
		}
		return false;
	}
	// 관찰자 삭제
	public void deleteUser(String userID) {
		// 예외 처리를 추가하면
		userList.remove(userID);
	}
	// 관찰자 패턴에서 notifyObservers에 해당
	// 채팅방에 있는 모든 사용자에게 최신 메시지를 전달함
	// 이전에 받은 메시지부터 최신 메시지까지 전달함 
	// 전송이 성공 못하면 userList를 갱신하지 않음
	// 사용자마다 전달해야 하는 메시지 수가 다를 수 있음
	// 특정 사용자는 현재 오프라인일 수 있음 (이 부분을 ChatServer가 처리함)
	public void updateUsers(ChatServer chatServer) {
		for(String userID : userList.keySet()) {
			int lastLogIndex = userList.get(userID) + 1;
			for(int i = lastLogIndex; i < roomLog.size(); i++) {
				if(chatServer.forwardMessage(userID, roomName, roomLog.get(i)))
					lastLogIndex += 1;
			}
			userList.put(userID, lastLogIndex - 1);
		}
	}
}
