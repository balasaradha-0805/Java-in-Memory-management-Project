package ecommerce;
import java.util.*;

public class Collections_Main_2 {	
	Scanner in = new Scanner(System.in);
	ArrayList<Product> all_products = new ArrayList<>();
	HashMap<String, Product> lookup_product_with_name = new HashMap<>();
	LinkedHashMap<ShoppingCart, Integer> shopping_cart = new LinkedHashMap<>(); // for insertion order
	ShoppingSystem sys = new ShoppingSystem();

	public void main(String args[]) {
		perform_menu_driven_operations();
	}

	public void perform_menu_driven_operations() {
		System.out.println(" \n \n Enter 1 to do operations");
		int op = in.nextInt();
		do {
			System.out.println("Menu driven program \n Enter \n 1. view all products \n 2. search product \n 3. add a new product \n 4. Add product to cart \n 14. exit ");
			System.out.println("\n\n enter your choice");
			int ch = in.nextInt();
			
			switch(ch) {
			case 1:
				view_products();
				break;
			case 2:
				search_products();
				break;
			case 3:
				add_new_products();
				break;
			case 4:
				add_products_to_cart();
				break;
			case 14:
				System.out.println("Exiting the Shopping application");
				op = 0;
				break;
			default:
				System.out.println("Invalid case");
				break;
			}
		} while(op != 0);
	}
	
	public void view_products() {
		if(all_products.isEmpty()) {
			System.out.println("No products to view ");
		}
		for(Product p : all_products) {
			sys.displayproduct(p);
		}
	}

	public void search_products() {
		System.out.println("Enter the product name to search");
		String prod_name = in.next();
		if(!lookup_product_with_name.containsKey(prod_name)) {
			System.out.println("Product is not available");
			return;
		}
		Product p = lookup_product_with_name.get(prod_name);
		System.out.println("Product found!!!");
		sys.displayproduct(p);
	}

	public void add_new_products() {
		String pn = in.next();
		double price = in.nextDouble();
		int st = in.nextInt();
		// need to do validation 
		Product np = new Electronics(pn,price,st);
		lookup_product_with_name.put(pn,np);
		all_products.add(np);
	}

	public void add_products_to_cart() {
		System.out.println("Enter the product to add to cart");
		String prod_name = in.next();
		
		System.out.println("Enter the product quantity");
		int q = in.nextInt();
		
		if(!lookup_product_with_name.containsKey(prod_name)) {
			System.out.println("Product is not available");
			return;
		}
		Product p = lookup_product_with_name.get(prod_name);
		ShoppingCart c = new ShoppingCart(p, q);
		System.out.println("Enter the product number like 1 for first product 2 for second product");
		int cart_no = in.nextInt();
		shopping_cart.put(c,cart_no);
		System.out.println("Product is added  to the cart");
		
	}
}