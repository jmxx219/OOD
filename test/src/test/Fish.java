
package test;

public abstract class Fish {
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
	
	public abstract void bite();
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}