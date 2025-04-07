package com.bofa.model;

public class TestServices {

	// Arithmetic Operations
    public static void addSubtractMultiplyDivide(int a, int b) {
        System.out.println("Arithmetic Operations:");
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b)); // Note: Ensure b is not zero
        System.out.println("Modulus: " + (a % b));
    }

    // Relational Operations
    public static void compareValues(int a, int b) {
        System.out.println("\nRelational (Comparison) Operations:");
        System.out.println(a + " == " + b + ": " + (a == b));
        System.out.println(a + " != " + b + ": " + (a != b));
        System.out.println(a + " > " + b + ": " + (a > b));
        System.out.println(a + " < " + b + ": " + (a < b));
        System.out.println(a + " >= " + b + ": " + (a >= b));
        System.out.println(a + " <= " + b + ": " + (a <= b));
    }

    // Logical Operations
    public static void logicalOperations(int a, int b) {
        boolean x = (a > b);
        boolean y = (a == b);
        
        System.out.println("\nLogical Operations:");
        System.out.println("x (a > b) = " + x + ", y (a == b) = " + y);
        System.out.println("x && y (AND): " + (x && y));
        System.out.println("x || y (OR): " + (x || y));
        System.out.println("!x (NOT): " + (!x));
    }

    // Bitwise Operations
    public static void bitwiseOperations(int a, int b) {
        System.out.println("\nBitwise Operations:");
        System.out.println("a & b (AND): " + (a & b));
        System.out.println("a | b (OR): " + (a | b));
        System.out.println("a ^ b (XOR): " + (a ^ b));
        System.out.println("~a (NOT a): " + (~a));
        System.out.println("a << 1 (Left Shift): " + (a << 1));
        System.out.println("a >> 1 (Right Shift): " + (a >> 1));
    }


    // If-Else Demonstration
    public static void checkNumber(int num) {
        System.out.println("\n--- If-Else Condition ---");
        if (num > 0) {
            System.out.println(num + " is a Positive Number.");
        } else if (num < 0) {
            System.out.println(num + " is a Negative Number.");
        } else {
            System.out.println(num + " is Zero.");
        }
    }

    // For Loop Demonstration
    public static void printNumbersUsingForLoop(int n)
    {
        System.out.println("Using For Loop");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
    }

    // While Loop Demonstration
    public static void printNumbersUsingWhileLoop(int n)
    {

        int i = 1;
        System.out.println("Using While Loop");
        while (i <= n) {
            System.out.print(i + " ");
            i++;
        }
    }

    // Do-While Loop Demonstration
    public static void printNumbersUsingDoWhileLoop(int n) 
    {

        int i = 1;
        System.out.println("Using Do While Loop");
        do {
            System.out.print(i + " ");
            i++;
        } while (i <= n);
    }

	
	public static void main(String[] args)
	{
		
		
	
	
	ServiceModel s = new ServiceModel();
    ServiceModel serviceObj = new ServiceModel(1001, true, "Customer Support",
            new String[]{"Retail Service 1", "Retail Service 2"},
            new String[]{"Digital Service 1", "Digital Service 2"},
            new String[]{"Lending Service 1", "Lending Service 2"});
	
    int num1 = 10, num2 = 5;
    
   

    addSubtractMultiplyDivide(num1, num2);
//    compareValues(num1, num2);
//    logicalOperations(num1, num2);
//    bitwiseOperations(num1, num2);
//    checkNumber(-3);
//    printNumbersUsingForLoop(5);
//    printNumbersUsingWhileLoop(5);
//    printNumbersUsingDoWhileLoop(5);
    
    System.out.println("I am in service class");
    
}

}


