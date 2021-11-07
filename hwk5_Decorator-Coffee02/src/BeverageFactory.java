import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file BeverageFactory.java
 * 생성 메소드가 정의되어 있는 클래스 (자바 reflaction 활용)
 */
public class BeverageFactory {
	private static Map<String, Restriction> restrictionTable = new HashMap<>(); // 존재하는 첨가물에 대한 제한 정보
	
	static class Restriction {
		public int maxAddition = 0 ; // 제한 횟수
		public Set<String> exclusionList = new HashSet<>(); // 추가할 수 없는 커피 목록
		public Restriction(int maxAdd, Set<String> excList) {
			this.maxAddition = maxAdd;
			this.exclusionList = excList;
		}
	}
	
//	public static void print() {
//		for(String key : restrictionTable.keySet()) {
//			Restriction exc = restrictionTable.get(key);
//			System.out.print("key: " + key + ", max: " + exc.maxAddition +", coffee: ");
//			Iterator<String> it = exc.exclusionList.iterator();
//			while (it.hasNext()) System.out.print(it.next()+" ");
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	public static Beverage createCoffee(String coffee, String... list) 
			throws Exception{
			//throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Beverage beverage = createBaseCoffee(coffee);
		beverage = decorateBaseCoffeeV2(beverage, list); // 장식자 제한 규칙에 따라 장식자를 추가한다.
		return beverage;
	}
	
	public static void addAdditionRestriction(String decorator, int maxAddition) {
		if(restrictionTable.containsKey(decorator)) { 
			// restrictionTable에 존재한다면 해당 장식자의 Restriction를 얻어와서 최대 제한 갯수를 넣는다.
			Restriction exec = restrictionTable.get(decorator);
			exec.maxAddition = maxAddition;
		}
		else { 
			// 존재하지 않는다면 Restriction을 새로 생성하여 put한다.
			restrictionTable.put(decorator, new Restriction(maxAddition, new HashSet<>()));
		}
	}
	
	public static void addCoffeeRestriction(String decorator, String coffee) {
		if(restrictionTable.containsKey(decorator)) { 
			// restrictionTable에 존재한다면 해당 장식자의 Restriction를 얻어와서 제한하는 커피 목록에 coffee를 추가한다.
			Restriction exec = restrictionTable.get(decorator);
			exec.exclusionList.add(coffee);
		}
		else {
			// 존재하지 않는다면 Restriction을 새로 생성하여 put한다.
			Set<String> excList = new HashSet<>();
			excList.add(coffee);
			restrictionTable.put(decorator, new Restriction(0, excList));
		}
	}
	
	// protected인 경우 생성자를 가지고 올 때는 getConstructor 대신에 getDeclaredConstructor 사용
	// reflection 사용하면 접근권한을 우회할 수 있음	
	private static Beverage createBaseCoffee(String coffee) throws Exception{
		//throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class<? extends Beverage> coffeeClass 
			= Class.forName(coffee).asSubclass(Beverage.class);
		if(coffeeClass.getSuperclass()!=Beverage.class||coffeeClass==CondimentDecorator.class)
			throw new IllegalArgumentException("Must use Concrete Decoretee");
		/*
		// public 생성자를 제공하는 경우
		Constructor<? extends Beverage> coffeeConstructor = decorateeClass.getConstructor();
		Beverage beverage = (Beverage)coffeeConstructor.newInstance();
		*/
		// 생성자가 protected, private인 경우
		Constructor<? extends Beverage> coffeeConstructor = coffeeClass.getDeclaredConstructor();
		//coffeeConstructor.setAccessible(true);
		Beverage beverage = (Beverage)coffeeConstructor.newInstance();
		return beverage;
	}
	
	private static Beverage decorateBaseCoffeeV1(Beverage beverage, String... list) throws Exception{
		//throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if(list.length==0) return beverage;
		Arrays.sort(list);
		// 더 효과적으로 할 수 있을까? Mocha가 여러 번 사용되면 이 과정이 반복됨
		// Decorator들을 대표하는 CondimentDeocorator 클래스가 없을 경우, 아래처럼 강건하게 작성할 수 없음
		
		for(String s: list){
			Class<? extends CondimentDecorator> condimentClass 
				= Class.forName(s).asSubclass(CondimentDecorator.class);
			
			// public 생성자를 제공하는 경우
			Constructor<? extends CondimentDecorator> condimentConstructor 
				= condimentClass.getConstructor(Beverage.class);
			beverage = (Beverage)condimentConstructor.newInstance(beverage);
			
			//Constructor<? extends CondimentDecorator> condimentConstructor 
			//	= condimentClass.getDeclaredConstructor(Beverage.class);
			//condimentConstructor.setAccessible(true);
			beverage = (Beverage)condimentConstructor.newInstance(beverage);
		}
		return beverage;
	}
	
	private static Beverage decorateBaseCoffeeV2(Beverage beverage, String... list) throws Exception{
		if(list.length==0) return beverage;
		Arrays.sort(list);
		Map<String, Constructor<? extends CondimentDecorator>> decoratorMap = new HashMap<>(11);
		
		String coffee = beverage.getClass().toString().split(" ")[1];
		Map<String, Integer> countDecorator = new HashMap<>();
		for(String s: list){
			if(restrictionTable.get(s).exclusionList.contains(coffee)) {
				throw new IllegalArgumentException(coffee + " cannot be decorated with this " + s);
			}
			else if(restrictionTable.get(s).maxAddition == 0 || countDecorator.getOrDefault(s, 0) < restrictionTable.get(s).maxAddition) {
				countDecorator.put(s, countDecorator.getOrDefault(s, 0) + 1);
			}

			if(!decoratorMap.containsKey(s)) {
				decoratorMap.put(s, 
					Class.forName(s).asSubclass(CondimentDecorator.class)
						.getDeclaredConstructor(Beverage.class));
			}
		}
		for(String s: list){
			if(countDecorator.get(s) > 0) {
				countDecorator.put(s, countDecorator.get(s) - 1);
				beverage = (Beverage)decoratorMap.get(s).newInstance(beverage);
			}
		}
		return beverage;
	}
}
