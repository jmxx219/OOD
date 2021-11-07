public class Fish {
	private int age;
	private String name;
	
	public Fish(){
		name = "Fish.";
		age = 1;
	}
	
	public Fish(int age, String name){
		this.age = age;
		this.name = name;
	}
	
	public void greeting(){
		System.out.println("Hi, I am " + name + ". I can swim.");
	}
	
	public String toString(){
		return "Name: " + name + " Age: " + age;
	}

}