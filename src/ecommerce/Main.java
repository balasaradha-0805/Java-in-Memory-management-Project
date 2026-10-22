package ecommerce;



public class Main {
	public static void main (String args[]) {
	
		//One interface or parent type can represent different implementations.	
	  Product laptop = new Electronics("Laptop", 50000, 5);
      Product shirt = new Clothing("T-Shirt", 1000, 10);

      ShoppingSystem sy = new ShoppingSystem();
      sy.displayproduct(laptop);
      sy.displayproduct(shirt);
      sy.displaytotalproducts();
      
      int q =2;
      double subtotal = laptop.getprice() * q;
      System.out.println("\nSubtotal: Rs. " + subtotal);
      
      // overloading and static binding
      System.out.println(laptop.calculateprice(2));
      System.out.println(laptop.calculateprice(2, 10));
      
      // aggregation
      ShoppingCart cart = new ShoppingCart(laptop, q);
      cart.displayCart();
      
      //interface and dynamic binding
      Paymentmethod pym = new UPI();
      Checkout c = new Checkout();
      c.processOrder(cart, pym);
      
      System.out.println("Remaining stock: " + laptop.getstock());
      
	}
}




