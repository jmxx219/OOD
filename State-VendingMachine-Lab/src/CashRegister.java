import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * 상태 패턴
 * 자판기가 유지하는 돈, 사용자가 투입한 돈, 거스름 돈을 나타내기 위해 사용
 */
public class CashRegister {
	private Map<Currency, Integer> register = new EnumMap<>(Currency.class);
	{
		for(var money: Currency.values()) register.put(money, 0);
	}
	private int balance = 0;
	
	public CashRegister() {}
	public void clear() {
		for(var money: Currency.values()) register.put(money, 0);
		balance = 0;
	}
	public void add(Currency money, int amount) {
		if(amount<=0) throw new IllegalArgumentException();
		int currentAmount = register.get(money);
		register.put(money, currentAmount+amount);
		balance += amount*money.value;
	}
	public void set(Currency money, int amount) {
		if(amount<0) throw new IllegalArgumentException();
		int diff = amount-register.get(money);
		register.put(money, amount);
		balance += diff*money.value;
	}
	public void remove(Currency money, int amount) {
		if(amount<=0) throw new IllegalArgumentException();
		int currentAmount = register.get(money);
		if(currentAmount<amount) throw new IllegalStateException();
		register.put(money, currentAmount-amount);
		balance -= amount*money.value;
	}
	public int getBalance() {
		return balance;
	}
	public int getAmount(Currency money) {
		return register.get(money);
	}
	public CashRegister getChange(int changeAmount) {
		if(changeAmount<0) throw new IllegalArgumentException(); 
		CashRegister changeRegister = new CashRegister();
		boolean flag = false; // 거스름액을 만들 수 있는지 여부
		Currency[] moneys = Currency.values();
		// 탐욕적 방법. 가장 큰 액면가부터 이용
		for(int i=moneys.length-1; i>=0; i--) {
			int currentAmount = register.get(moneys[i]);
			if(currentAmount==0) continue;
			int amountNeeded = 0;
			if(moneys[i].value>changeAmount) continue;
			else {
				amountNeeded = changeAmount/moneys[i].value;
			}
			int amountUsed = (amountNeeded>=currentAmount)? currentAmount: amountNeeded;
			changeRegister.add(moneys[i], amountUsed);
			changeAmount -= amountUsed*moneys[i].value;
			if(changeAmount==0) {
				flag = true;
				break;
			}
		}
		if(!flag) changeRegister.clear();
		return changeRegister;
	}
	
	public Set<Entry<Currency, Integer>> getEntrySet(){
		return register.entrySet();
	}
	public void debugPrint() {
		for(var money: register.entrySet()) {
			System.out.println(money.getKey()+": "+money.getValue());
		}
		System.out.printf("총금액: %,d원%n", balance);
	}
}
