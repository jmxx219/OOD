import java.util.HashMap;
import java.util.Map;

public abstract class StringFactory {
	private static final Map<Integer,String> stringPool = new HashMap<>();
	public static String getInstance(String s) {
		Integer key = Integer.valueOf(s.hashCode());
		if(!stringPool.containsKey(key)) {
			stringPool.put(key, new String(s));
		}
		return stringPool.get(key);
	}
}
