import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class KeyMultiton {
	private static final Map<String, KeyMultiton> registry = new HashMap<>();
	private static final int NUMBEROFINSTANCE = 3;
	
	private int n;
	private KeyMultiton(int n) { this.n = n; }
	public static KeyMultiton getInstance(String key) {
		if(registry.containsKey(key)) {
			return registry.get(key);
		}
		else {
			//synchronized (KeyMultiton.class) {
				if(registry.size()>=NUMBEROFINSTANCE) 
					throw new IllegalArgumentException();
				KeyMultiton instance = new KeyMultiton(ThreadLocalRandom.current().nextInt());
				registry.put(key, instance);
				return instance;
			//}
		}
	}
	public int get() {
		return n;
	}
	//Debug용
	public static void print() {
		System.out.print("Keys: ");
		for(var key: registry.keySet()) {
			System.out.print(key+", ");
		}
		System.out.println();
	}
}
