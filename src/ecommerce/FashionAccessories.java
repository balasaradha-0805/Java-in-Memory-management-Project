package ecommerce;

public record FashionAccessories (
	int id,
	String name,
	double price
	
) {
	 public FashionAccessories {
	        if (name == null || name.isBlank()) {
	        	throw new IllegalArgumentException("Product name cannot be empty");
	        }
	        if (price < 0) {
	        	throw new IllegalArgumentException("Price cannot be negative");
	        }
	    }
}



// these are the generated methods od the record class 


//C:\SpringBoot_workspace\Demo>javap -p -classpath bin ecommerce.FashionAccessories
//Compiled from "FashionAccessories.java"
//public final class ecommerce.FashionAccessories extends java.lang.Record {
//  private final int id;
//  private final java.lang.String name;
//  private final java.lang.String brand;
//  private final double price;
//  public int id();
//  public java.lang.String name();
//  public java.lang.String brand();
//  public double price();
//  public final java.lang.String toString();
//  public final int hashCode();
//  public final boolean equals(java.lang.Object);
//  public ecommerce.FashionAccessories(int, java.lang.String, java.lang.String, double);
//}
//
//C:\SpringBoot_workspace\Demo>



//
//
//C:\SpringBoot_workspace\Demo>javap -c -p -classpath bin ecommerce.FashionAccessories


// -c displays byte code instructions aswell

//Compiled from "FashionAccessories.java"
//public final class ecommerce.FashionAccessories extends java.lang.Record {
//  private final int id;
//
//  private final java.lang.String name;
//
//  private final java.lang.String brand;
//
//  private final double price;
//
//  public int id();
//    Code:
//         0: aload_0
//         1: getfield      #14                 // Field id:I
//         4: ireturn
//
//  public java.lang.String name();
//    Code:
//         0: aload_0
//         1: getfield      #19                 // Field name:Ljava/lang/String;  
//         4: areturn
//
//  public java.lang.String brand();
//    Code:
//         0: aload_0
//         1: getfield      #21                 // Field brand:Ljava/lang/String; 
//         4: areturn
//
//  public double price();
//    Code:
//         0: aload_0
//         1: getfield      #24                 // Field price:D
//         4: dreturn
//
//  public final java.lang.String toString();
//    Code:
//         0: aload_0
//         1: invokespecial #41                 // Method java/lang/Record."<init>":()V
//         4: aload_0
//         5: iload_1
//         6: putfield      #14                 // Field id:I
//         9: aload_0
//        10: aload_2
//        11: putfield      #19                 // Field name:Ljava/lang/String;            14: aload_0
//        15: aload_3
//        16: putfield      #21                 // Field brand:Ljava/lang/String;   
//        19: aload_0
//        20: dload         4
//        22: putfield      #24                 // Field price:D
//        25: return
//}
//C:\SpringBoot_workspace\Demo>