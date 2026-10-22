package ecommerce;
import java.util.*;
//throw → use it when you want to actually raise an exception.
//throws → use it in a method definition to declare that the method may pass an exception to its caller.
public class CollectionswithExceptions {	
	Scanner in = new Scanner(System.in);
	ArrayList<Product> all_products = new ArrayList<>();
	HashMap<String, Product> lookup_product_with_name = new HashMap<>();
	LinkedHashMap<ShoppingCart, Integer> shopping_cart = new LinkedHashMap<>(); // for insertion order
	ShoppingSystem sys = new ShoppingSystem();

	public void main(String[] args) {
		 CollectionswithExceptions obj = new CollectionswithExceptions();
		    obj.perform_menu_driven_operations();
	}

	public void perform_menu_driven_operations() {
		System.out.println(" \n \n Enter 1 to do operations");
		int op = in.nextInt();
		try {
		do {
			System.out.println("Menu driven program \n Enter \n 1. view all products \n 2. search product \n 3. add a new product \n 4. Add product to cart \n 5. initializing products \n 6.\n  14. exit ");
			System.out.println("\n\n enter your choice");
			int ch = in.nextInt();
			
			switch(ch) {
			case 1:
				view_products();
				break;
			case 2:
				try {
				search_products();
				}catch(ProductNotFoundException e) {
					System.out.println("Error + e.getMessage()");
				}
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
		}catch(InputMismatchException e) {
			System.out.println("Please enter number not text");
		}finally {
			in.close();
			System.out.println("Closing the scanner object");
		}
	}
	
	public void view_products() {
		try {
		if(all_products.isEmpty()) {
			throw new ProductNotFoundException("No products are available");
		}
		for(Product p : all_products) {
			sys.displayproduct(p);
		}
		}catch (ProductNotFoundException e) {
			System.out.println("Error -> " + e.getMessage());
		}
	}

	public void search_products() throws ProductNotFoundException {
		System.out.println("Enter the product name to search");
		String prod_name = in.next();
		Product p = lookup_product_with_name.get(prod_name);
		if(p==null) {
			throw new ProductNotFoundException("Product not found");
		}
		System.out.println("Product found!!!");
		sys.displayproduct(p);
	}

	public void add_new_products() {
		try {
		System.out.println("Enter name, price, stock");
		String pn = in.next();
		double price = in.nextDouble();
		int st = in.nextInt();
		// need to do validation 
		 if (price <= 0 || !Double.isFinite(price)) {
             throw new IllegalArgumentException("Price must be positive and finite.");
         }
		 if (st < 0) {
             throw new Invalid_Product_Exception("Stock cannot be negative.");
         }
         if (lookup_product_with_name.containsKey(pn)) {
        	 throw new Invalid_Product_Exception("Product already exists.");
         }

		Product np = new Electronics(pn,price,st);
		lookup_product_with_name.put(pn,np);
		all_products.add(np);
		}catch(InputMismatchException e) {
			System.out.println("Enter the valid price and stock quantity");
		}catch(IllegalArgumentException e) {
			System.out.println("Error " + e.getMessage());
		}catch(Invalid_Product_Exception e) {
			System.out.println("Error " + e.getMessage());
		}
	}

	public void add_products_to_cart() {
		try {
		System.out.println("Enter the product to add to cart");
		String prod_name = in.next();
		
		Product p = lookup_product_with_name.get(prod_name);
		if(p==null) {
			throw new ProductNotFoundException("Product not found");
		}
		
		System.out.println("Enter the product quantity");
		int q = in.nextInt();
		   if (q <= 0) {
	            throw new IllegalArgumentException("Quantity must be greater than zero.");
	        }
	        if (q > p.getstock()) {
	            throw new IllegalArgumentException("Insufficient stock. Available stock: " + p.getstock());
	        }
		ShoppingCart c = new ShoppingCart(p, q);
		System.out.println("Enter the product number like 1 for first product 2 for second product");
		int cart_no = in.nextInt();
		shopping_cart.put(c,cart_no);
		System.out.println("Product is added  to the cart");
		}catch (InputMismatchException e) {
	        System.out.println("Error: Enter a valid number.");
	        in.nextLine(); 

	    } catch (ProductNotFoundException e) {
	        System.out.println("Error: " + e.getMessage());

	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	}
}