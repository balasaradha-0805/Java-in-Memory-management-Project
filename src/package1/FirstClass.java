package package1;
class Student {
	String name;
	int age;
	
	void display() {
		System.out.println(name + ""+age);
	}
	
}
public class FirstClass {
	static void changeName(Student s) {
		s.name = "shdsjsa";
	}
	public static void main(String args[]) {
		Student s1 = new Student();
		s1.name ="bala";
		s1.age = 21;
		s1.display();
		changeName(s1);
		System.out.println(s1.name);
		s1.display();
		
	}
}
