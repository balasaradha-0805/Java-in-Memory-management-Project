package ecommerce;

public class ShoppingSystem {
	public void displayproduct(Product p) {
		System.out.println("	Name :	"+ p.getname());
		System.out.println("	Price :	 "+ p.getprice());
		System.out.println("	Discount"	+ p.getdiscount());
		//explicit type casting
		if(p instanceof Electronics) {
			Electronics i = (Electronics) p;
			i.showwarranty();
		}
		System.out.println("\n");
	}
	public void displaytotalproducts() {
		System.out.println("Total products"+ Product.totalproducts);
	}
}
