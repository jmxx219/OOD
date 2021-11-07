import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Time.java
 * 불변클래스
 * 객체 풀 실습 
 */
public class Time {
	
	private final int hour;
	private final int minute;
	private final int second;
	
	private static final Map<Integer, Time> timePool = new HashMap<>();
	
	private Time(int hour) {
		this(hour,0,0);
	}
	private Time(int hour, int minute) {
		this(hour,minute,0);
	}
	private Time(int hour, int minute, int second) {
		this.hour = (hour>=0&&hour<24)? hour: 0;
		this.minute = (minute>=0&&minute<60)? minute: 0;
		this.second = (second>=0&&second<60)? second: 0;
	}
	
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public int getSecond() {
		return second;
	}
	@Override public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	
	public static Time of(int hour) {
		return of(hour, 0, 0);
	}
	public static Time of(int hour, int minute) {
		return of(hour, minute, 0);
	}
	public static Time of(int hour, int minute, int second) {
		int key = Arrays.hashCode(new int[]{hour, minute, second});
		if(!timePool.containsKey(key)) {
			timePool.put(key, new Time(hour, minute, second));
		}
		return timePool.get(key);
	}
}
