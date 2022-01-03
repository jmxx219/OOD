import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfixExpressionValidator {
	private ArrayList<Predicate<String>> predicates = new ArrayList<>();
	private ArrayList<String> errorMessages = new ArrayList<>();
	
	public InfixExpressionValidator() {
		predicates.add(s->hasIllegalCharacter(s));
		predicates.add(s->hasInvalidSequence(s));
		predicates.add(s->hasMissingOperator(s));
		predicates.add(s->hasInvalidParenthesis(s));
		errorMessages.add("사용가능하지 않은 문자가 포함되어 있습니다.");
		errorMessages.add("연산자가 올바르지 않은 식입니다.");
		errorMessages.add("피연산자가 올바르지 않은 식입니다.");
		errorMessages.add("괄호가 일치하지 않습니다.");
	}
	public Optional<String> test(String infix) {
		for(int i=0; i<predicates.size(); i++) {
			if(predicates.get(i).test(infix)) {
				return Optional.of(errorMessages.get(i));
			}
		}
		return Optional.empty();
	}
	private static boolean hasIllegalCharacter(String infix) {
		String illegal = infix.replaceAll("[\\(\\)\\d\\+\\-\\*/\\s]", "");
		return illegal.length()>0;
	}
	protected static boolean hasInvalidSequence(String infix) {
		Pattern p = Pattern.compile("[\\+\\-\\*/]{2,}");
		Matcher m = p.matcher(infix.strip().replaceAll("\\s+", ""));
		while(m.find()) {
			if(m.group().equals("--")) continue;
			else return true;
		}
		return false;
	}
	protected static boolean hasMissingOperator(String infix) {
		infix = infix.strip().replaceAll("\\d", "D").replaceAll("(D)+","D")
			.replaceAll("\\s", "");
		Pattern p = Pattern.compile("(D){2,}");
		Matcher m = p.matcher(infix);
		return m.find();
	}
	private static boolean hasInvalidParenthesis(String infix) {
		// 괄호 외 모두 제거
		String parenthesis = infix.replaceAll("[^\\(\\)]", "");
		Stack<Character> check = new Stack<>();
		if(parenthesis.length()%2==1) return true;
		char[] P = parenthesis.toCharArray();
		for(var c: P) {
			switch(c) {
			case '(': check.push(c); break;
			default: 
				if(check.empty()) return true;
				check.pop();
			}
		}
		return !check.empty();  
	}
	public static String replaceUnaryMinus(String infix) {
		infix = infix.strip().replaceAll("\\s+","");
		System.out.println(infix);
		char[] tmp = infix.toCharArray();
		for(int i=0; i<tmp.length; i++) {
			if((i==0&&tmp[i]=='-')|| 
				(tmp[i]=='-'&&!Character.isDigit(tmp[i-1]))) tmp[i]='@';
		}
		return new String(tmp);
	}
}
