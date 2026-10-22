package ecommerce;

public class ShoppingCart {
	private Product p;
	private int quantity;
	
	public ShoppingCart(Product p, int quantity) {
		this.p=p;
		this.quantity = quantity;
	}
	public double gettotal() {
		return p.calculateprice(quantity);
	}
	public double getfinaltotal() {
		return p.calculateprice(quantity, p.getdiscount());
	}
	public void displayCart() {
		System.out.println("Product name:" +p.getname());
		System.out.println("Quantity "+quantity);
		System.out.println("Total "+ gettotal());
		System.out.println("Final amount"+getfinaltotal());
		
	}
	public void updatestock() {
		p.reducestock(quantity);
	}
	
}
