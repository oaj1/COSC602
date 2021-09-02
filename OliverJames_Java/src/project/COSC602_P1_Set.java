
//This program is used to test the MySet class
package project;

import structure.MySet;

/**
 * Class utilized to test the MySet set ADT
 * @author ollie
 *
 */
public class COSC602_P1_Set {
	
	public static void test() {
		
		System.out.println("COSC602 Project 1 Set testing Started ===============>");
		System.out.println();
		
		MySet fibonacciNumSet = new MySet();//original num Set
		
		MySet  primeNumSet = new MySet();
		
		
		int temp1 = 0, temp2 =1, fibNum;
		
		fibonacciNumSet.add(temp1);
		fibonacciNumSet.add(temp2);
		
		System.out.println("Add the fibonnaci numbers");
		//For loop for original FibSet...How to print out the results?Is my issue the toString method?
		for(int i = 2; i < 30; ++i) {
			
			fibNum = temp1 + temp2;
			
			temp1 = temp2;
			
			temp2 = fibNum;
			
			fibonacciNumSet.add(fibNum);
			//System.out.print(fibonacciNumSet.toString() + " ");//print out the fibonacciaNumSet
			
			
		}
		
		System.out.println(fibonacciNumSet);
		System.out.println();
		
		System.out.println("Add the prime numbers");
		//For loop for the prime num FibSet
		for(int i = 2; i < 30; ++i) {
			boolean isPrime = true;
			for(int j = 2; j < i; ++j  ) {
			//condition for non prime number
				if (i % j == 0) {
					isPrime = false;
					break;
			}
			
				if (isPrime) {
			
				primeNumSet.add(i);
		}
		
	}
		}
		System.out.println(primeNumSet);
		System.out.println();
		
		System.out.println("The following is the union");
		System.out.print(fibonacciNumSet.union(primeNumSet)+ " ");
		System.out.println();
		
		System.out.println("The following is the intersection");
		System.out.print(fibonacciNumSet.intersection(primeNumSet)+ " ");
		System.out.println();
		
		System.out.println("The following checks whether PrimeNumSet is a subset of FibNum. If not, the PrimeNumSet number, that is NOT"
				+ " a subset of FibNum, will be displayed.");
		System.out.print(primeNumSet.subSetOf(fibonacciNumSet)+ " ");
		System.out.println();
		
		System.out.println();
		System.out.println("The following is the Symmetric Difference");
		System.out.print(fibonacciNumSet.symmetricDifference(primeNumSet)+ " ");
		System.out.println();
		

}
}
