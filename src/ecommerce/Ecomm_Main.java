package ecommerce;
import java.util.*;
import java.util.stream.Collectors;

//throw → use it when you want to actually raise an exception.
//throws → use it in a method definition to declare that the method may pass an exception to its caller.
public class Ecomm_Main {	
	Scanner in = new Scanner(System.in);
	ArrayList<Product> all_products = new ArrayList<>();
	HashMap<String, Product> lookup_product_with_name = new HashMap<>();
	LinkedHashMap<ShoppingCart, Integer> shopping_cart = new LinkedHashMap<>(); // for insertion order
	ShoppingSystem sys = new ShoppingSystem();
	Random random = new Random();

	public void main(String[] args) {
		Ecomm_Main obj = new Ecomm_Main();
		    obj.perform_menu_driven_operations();
	}

	public void perform_menu_driven_operations() {
		int op = 1;
		try {
		do {
			System.out.println("Menu driven program \n Enter \n 1. view all products \n 2. search product \n 3. add a new product \n 4. Add product to cart \n 5. initializing products \n 6. filtering products by category \n 7.sorting by price \n 8.finding top N expensive products \n 9.Grouping Products by category \n 14. exit ");
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
					System.out.println("Error "+ e.getMessage());
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
				
			case 5:
				initializingProducts();
				break;
				
			case 6:
				filteringProductsByCategory();
				break;
			case 7:
				sortingByPrice();
				break;
				
			case 8:
				findingTopNExpensiveProducts();
				break;
				
			case 9:
				groupingProductsByCategory();
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

	    in.nextLine(); // Consume leftover newline from nextInt()
	    String prod_name = in.nextLine().trim();
	    Optional<Product> product =
	            Optional.ofNullable(lookup_product_with_name.get(prod_name));

	    product.ifPresentOrElse(
	        p -> {
	        	System.out.println("Product found!");
	            sys.displayproduct(p);
	        }, () -> System.out.println("Product not found: " + prod_name)
	    );
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
	
	public void initializingProducts() {
		if(!all_products.isEmpty()) {
			System.out.println("Products are already initialized ");
			return;
		}
		int min = 10;
		int max = 30;
		 List<Integer> numbers = new ArrayList<>();
	        for (int i = min; i <= max; i++) {
	            numbers.add(i);
	        }
	        Collections.shuffle(numbers);
	        
	        List<Double> EPrices = random.doubles(10, 1000.0, 5000.0)
	                .map(price -> Math.round(price * 100.0) / 100.0) // Round to 2 decimal places
	                .boxed() // Convert DoubleStream to Stream<Double>
	                .collect(Collectors.toList());
	        
		for(int i=1;i<=10;i++) {
			String name = "Electronic product "+ i;
			int stock = numbers.get(i-1);
			
			Product p = new Electronics(name, EPrices.get(i-1), stock);
			all_products.add(p);
			
			lookup_product_with_name.put(name, p);
		}
		   List<Double> CPrices = random.doubles(10, 500.0, 2000.0)
	                .map(price -> Math.round(price * 100.0) / 100.0) // Round to 2 decimal places
	                .boxed() // Convert DoubleStream to Stream<Double>
	                .collect(Collectors.toList());
		
		for(int i=1;i<=10;i++) {
			String name = "Clothing product "+ i;

			int stock = numbers.get(i+9);
			
			Product p = new Clothing(name, CPrices.get(i-1), stock);
			all_products.add(p);
			
			lookup_product_with_name.put(name, p);
		}
		
		System.out.println("10 Electronics product and 10 Clothing product intialized successfully");
	}
	
	public void filteringProductsByCategory() {
		System.out.println("Enter the category to filter");
		String category = in.next();
		
		all_products.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).forEach(p-> sys.displayproduct(p));
	}
	public void sortingByPrice() {
		System.out.println("Enter A for Ascending order and D for Descending Order (Caps only)");
		char ch = in.next().charAt(0);
		
		if(ch == 'A') {
			all_products.stream().sorted((p1,p2) -> Double.compare(p1.getprice() , p2.getprice())).forEach(p-> sys.displayproduct(p));
		}else {
			all_products.stream().sorted((p1,p2) -> Double.compare(p2.getprice() , p1.getprice())).forEach(p-> sys.displayproduct(p));
		}
		
	}
	public void findingTopNExpensiveProducts() {
		System.out.println("Enter N");
		int n = in.nextInt();
		
		all_products.stream().sorted((p1,p2) -> Double.compare(p2.getprice() , p1.getprice())).limit(n).forEach(p-> sys.displayproduct(p));
		
	}
	public void groupingProductsByCategory() {
		  Map<String, List<Product>> groupedProducts =all_products.stream().collect(Collectors.groupingBy(Product::getCategory));
		 
		  groupedProducts.forEach((category, products) -> {
		        System.out.println("\nCategory: " + category);
		        products.forEach(p -> sys.displayproduct(p));
		    });
	}
	}