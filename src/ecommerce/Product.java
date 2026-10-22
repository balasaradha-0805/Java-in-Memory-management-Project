package ecommerce;

abstract class Product {
	private String name;
	private double price;
	private int stock;
	
	static int totalproducts =0;
	static {
		System.out.println("Ecommerce initialized\n");
	}
	{
		totalproducts++;
		System.out.println("Product initialized\n");
	}
	Product(String name, double price, int stock){
		this.name = name;
		this.price=price;
		this.stock = stock;
	}
	
	public String getname() {
		return name;
	}
	public double getprice() {
		return price;
	}
	public int getstock() {
		return stock;
	}
	
	public void reducestock(int quantity) {
		stock-=quantity;
	}
	//method overloading
	public double calculateprice(int quantity, double discount) {
		return price*quantity *( 1-discount/100);
	}
	public double calculateprice(int quantity) {
		return price*quantity;
	}
	
	//abstract fn 
	abstract double getdiscount();
	abstract String getCategory();
}

class Electronics extends Product{
	Electronics(String name , double price, int stock){
		super(name, price , stock);
	}
	@Override
	 double getdiscount() {
		return 10;
	}
	void showwarranty() {
        System.out.println("	1-year warranty");
    }
	@Override 
	String getCategory() {
		return "Electronics";
	}
}

class Clothing  extends Product{
	Clothing(String name , double price, int stock){
		super(name, price , stock);
	}
	@Override
	 double getdiscount() {
		return 20;
	}
	@Override 
	String getCategory() {
		return "Clothing";
	}
	
}
//payment interface exposes pay() without specifying how each payment method works
interface Paymentmethod {
	void pay(double amt);
}

class UPI implements Paymentmethod{
	public void pay(double amt) {
		System.out.println("the amount is "+amt+"paid using UPI ");
	}
}
class Cash implements Paymentmethod{
	public void pay(double amt) {
		System.out.println("the amount is "+amt+"paid using cash ");
	}
}

class Order {
	Product p;
	int quantity;
	
	Order(Product p, int quantity){
		this.p=p;
		this.quantity=quantity;
	}
}
