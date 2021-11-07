import java.util.concurrent.ThreadLocalRandom;

public class PoolMultiton {	
	private static final int NUMBEROFINSTANCE = 3;
	private static PoolMultiton[] objectPool = new PoolMultiton[NUMBEROFINSTANCE];
	private static int size = 0;
	private PoolMultiton() {}
	public static PoolMultiton getInstance() {
		if(size<NUMBEROFINSTANCE) {
			objectPool[size] = new PoolMultiton();
			++size;
			return objectPool[size-1];
		}
		else {
			return objectPool[ThreadLocalRandom.current().nextInt(NUMBEROFINSTANCE)];
		}
	}
}
