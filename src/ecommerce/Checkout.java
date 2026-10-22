package ecommerce;

public class Checkout {
	public void processOrder(ShoppingCart cart , Paymentmethod py) {
			cart.updatestock();
			py.pay(cart.getfinaltotal());
			System.out.println("Order Completed");
		}
	}
