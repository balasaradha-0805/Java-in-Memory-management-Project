package ecommerce;

public class Record_Main {
	public static void main(String[] args) {
FashionAccessories f1 = new FashionAccessories(1,"Stud1",230.0);
FashionAccessories f2 = new FashionAccessories(1,"Stud1",230.0);
FashionAccessories f3 = new FashionAccessories(2,"Stud2",240.0);
FashionAccessories f4 = new FashionAccessories(3,"Stud3",250.0);
FashionAccessories f5 = new FashionAccessories(4,"Stud4",260.0);
FashionAccessories f6 = new FashionAccessories(5,"Stud6",270.0);


System.out.println("Displaying the generated methods");
System.out.println("id" + f1.id());
System.out.println("name" + f3.name());
System.out.println(" \n to string \n"+ f4);
System.out.println("\n Equals fn " + f1.equals(f2));
System.out.println("Equals fn false case " + f5.equals(f6));
System.out.println("Hashcode  f6 " + f6.hashCode());
System.out.println("Hashcode f5 " + f5.hashCode());

try {
    FashionAccessories f7 = new FashionAccessories(104, "", 500.0);
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}

try {
    FashionAccessories f8 = new FashionAccessories(105, "Watch", -100.0);
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
	}

}