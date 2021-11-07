
public class StringFactoryTest {

	public static void main(String[] args) {
		String s1 = StringFactory.getInstance("apple");
		String s2 = StringFactory.getInstance("apple");
		String s3 = StringFactory.getInstance("paple");
		String s4 = StringFactory.getInstance("banana");
		String s5 = StringFactory.getInstance("banana");
		
		System.out.println(s1==s2);
		System.out.println(s3==s2);
		System.out.println(s4==s5);
	}

}
