/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * BankAccount.java: 은행계좌
 * 실습1-1) SerialNumberGenerator 
 *  > 내부 클래스 형태로 싱글톤으로 정의하여 계좌번호를 할당하시오.
 * 실습1-2) AccountNumberGenerator
 *  > 열거형으로 정의하여 계좌번호를 할당하시오 
 */
public class BankAccount {
	private int balance = 0;
	private int accountNumber;
	public BankAccount(){
		// accountNumber = SerialNumberGenerator.getInstance().getNext();
		accountNumber = AccountNumberGenerator.UNIQUE.getNext();
	} // BankAccount()
	public BankAccount(int amount){
		this();
		deposit(amount);
	} // BankAccount(int)
	public int getBalance(){
		return balance;
	} // getBalance()
	public void deposit(int amount){
		assert(amount>0);
		balance += amount;
	} // deposit(int)
	public void withdraw(int amount){
		assert(amount>0);
		if(amount>0 && balance>=amount) balance -= amount;
		else if(amount>balance) throw new InsufficientFundException(
			String.format("잔액부족> 현재잔액: %d, 인출시도액: %d", balance, amount));
	} // withdraw(int)
	@Override
	public String toString(){
		return String.format("계좌번호: %06d > 잔액: %,d원", accountNumber, balance);
	}
	public static void transfer(BankAccount from, BankAccount to, int amount){
		if(from==null||to==null) return;			
		try{			
			from.withdraw(amount);			
			to.deposit(amount);		
		}		
		catch(InsufficientFundException e){	
			System.out.println(e.getMessage());		
		}	
	} // transfer(BankAccount, BankAccount, int)
}
