import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file ChatServer.java 
 * 채팅 프로그램에서 통신 서버와 데이터베이스 서버 역할을 함 
 * 채팅룸 목록, 사용자 목록 유지
 */
public class ChatServer {
	// 데이터베이스 역할을 위한 두 개의 맵
	private Map<String, ChatRoom> chatRooms = new HashMap<>();
	private Map<String, User> users = new HashMap<>();
	private static ChatServer uniqueInstance = null;
	private ChatServer() {}
	// 싱글톤으로 모델링, 전역변수처럼 사용할 수 있음
	public static ChatServer getServer() {
		if(uniqueInstance==null) uniqueInstance = new ChatServer();
		return uniqueInstance;
	}
	
	// 데이터베이스 역할을 위한 메소드들
	public void addRoom(String roomName) {
		if(!chatRooms.containsKey(roomName)) // 간단 중복 검사
			chatRooms.put(roomName, new ChatRoom(roomName));
	}
	public Optional<ChatRoom> getRoom(String roomName) {
		return Optional.ofNullable(chatRooms.get(roomName));
	}
	public void addUser(User user) {
		if(!users.containsKey(user.getUserID())) // 간단 중복 검사
			users.put(user.getUserID(),user);
	}
	public Optional<User> getUser(String userID) {
		return Optional.ofNullable(users.get(userID));
	}
	public Collection<User> getUsers(){
		return users.values();
	}
	public void addUserToRoom(String userID, String roomName) {
		// 예외 처리 추가
		User user = users.get(Objects.requireNonNull(userID));
		if(user == null) throw new IllegalArgumentException("가입되지 않은 유저");
		
		ChatRoom chatRoom = chatRooms.get(Objects.requireNonNull(roomName));
		if(chatRoom == null) throw new IllegalArgumentException("가입되지 않은 채팅방");
		
		
		if(chatRoom.addUser(userID)) // 간단 중복 검사
			user.joinRoom(roomName);
	}
	public void deleteUserFromRoom(String userID, String roomName) {
		// 예외 처리 추가 
		User user = users.get(Objects.requireNonNull(userID));
		if(user == null) throw new IllegalArgumentException("가입되지 않은 유저");
		
		ChatRoom chatRoom = chatRooms.get(Objects.requireNonNull(roomName));
		if(chatRoom == null) throw new IllegalArgumentException("가입되지 않은 채팅방");
		
		chatRoom.deleteUser(userID);
		user.leaveRoom(roomName);
	}
	
	// 통신 서버 역할을 위한 메소드들
	
	// 사용자가 메시지를 전송할 때 사용하는 메소드
	// 사용자가 전송한 메시지를 ChatRoom에 추가함
	// ChatRoom은 관찰자 패턴을 이용하여 가입된 모든 사용자에게 메시지를 전달함
	public void sendMessage(String roomName, ChatMessage message) {
		ChatRoom chatRoom = chatRooms.get(roomName);
		chatRoom.newMessage(message, this); 
	}
	// ChatRoom에서 각 사용자에게 메시지를 실제 전달할 때 사용
	public boolean forwardMessage(String destID, String roomName, ChatMessage message) {
		User receiver = users.get(destID);
		if(receiver.isOnline()) {
			receiver.update(roomName, message);
			return true;
		}
		else return false;
	}
}
