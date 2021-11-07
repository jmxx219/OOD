import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * @file ChatMessage.java
 * 채팅 메시지 클래스 
 * 전송자와 전송 메시지 유지
 */
public record ChatMessage(String senderID, String content) {
	public ChatMessage{
		Objects.requireNonNull(senderID);
		Objects.requireNonNull(content);
	}
}
