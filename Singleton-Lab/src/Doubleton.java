import java.util.concurrent.ThreadLocalRandom;

public enum Doubleton {
	FIRST, SECOND;
	private static boolean flag = true;
	public static Doubleton getInstance() {
		flag = !flag;
		return flag? FIRST: SECOND;
		// return (ThreadLocalRandom.current().nextBoolean())? FIRST: SECOND;
	}
}
