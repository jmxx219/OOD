import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 관찰자 패턴 실습
 * User.java 
 * 채팅 프로그램에서 사용자 역할을 함 
 * 가입된 채팅방마다 채팅메시지 목록 유지
 * 채팅방에 대해서는 관찰자, 채팅창에 대해서는 subject
 * @author 2019136072 손지민
 *
 */
public class User{
	private String userID;
	private boolean isOnline = true;
	// Map<채팅방, 채팅메시지 목록>
	private Map<String, List<ChatMessage>> roomLogs = new HashMap<>(); // 사용자가 가입한 채팅방 대화목록 유지
	private UserChatWindow chatWindow;
	
	public User(String userID) {
		this.userID = Objects.requireNonNull(userID);
	}
	
	public String getUserID() {
		return userID; 
	}
	public void setOnline(boolean flag) {
		isOnline = flag;
	}
	public boolean isOnline() {
		return isOnline;
	}
	
	public void joinRoom(String roomName) {
		if(roomLogs.containsKey(Objects.requireNonNull(roomName))) return;
		else roomLogs.put(roomName, new ArrayList<ChatMessage>());
	}
	public void leaveRoom(String roomName) {
		if(!roomLogs.containsKey(Objects.requireNonNull(roomName)))
			 throw new IllegalArgumentException("가입되지 않은 채팅방");
		roomLogs.remove(roomName);
	}
	
	public List<ChatMessage> getRoomLog(String roomName) {
		if(!roomLogs.containsKey(Objects.requireNonNull(roomName)))
			 throw new IllegalArgumentException("가입되지 않은 채팅방");
		return roomLogs.get(roomName);
	}
	public void clearRoom(String roomName) {
		List<ChatMessage> roomLog = roomLogs.get(Objects.requireNonNull(roomName));
		if(roomLog!=null) roomLog.clear();
		else throw new IllegalArgumentException("가입되지 않은 채팅방");
	}
	
	public String[] getRooms() {
		String[] roomNames = new String[roomLogs.size()];
		roomLogs.keySet().toArray(roomNames);
		return roomNames;
	}
	
	public void setView(UserChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}
	public void notifyView(String roomName) {
		chatWindow.update(roomName);
	}
	
	public void update(String roomName, ChatMessage message) { // 관찰자가 사용자에게 새 메시지를 알리기 위해 사용
		if(roomLogs.containsKey(Objects.requireNonNull(roomName))) {
			roomLogs.get(roomName).add(Objects.requireNonNull(message));
			notifyView(roomName);
		}
		else throw new IllegalArgumentException("가입되지 않은 채팅방");
	}
}
