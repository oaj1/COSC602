package project;

import java.io.*;
import structure.*;
import java.util.*;

/**
 * This class will handle the input.txt doc comprised of digits and operators
 * and perform the operation using the MyDeque and MyStack classes. If the
 * operation is not valid, it will return a not valid operation method
 * if valid, it will perform various traversal methods and an evaluation
 * @author Oliver James
 *
 */
@SuppressWarnings("unused")
public class COSC60_P4_ExpressionTree {
	
	/**
	 * 
	 * @param c, char variable representing the operator
	 * @return numeric value, representing the order of operation, higher number is
	 * higher order of operation
	 */
	public static int orderOfOp(char c) {

		if (c == '+' || c == '-') {
			return 1;
		}

		if (c == '*' || c == '/' || c == '%') {
			return 2;
		}

		else {
			return -1;
		}
	}
	
	

	public static void test() throws IOException {

		String inputFile = "../COSC602_P4_ExpressionInput.txt";// input file to read
		File outputFile = new File("../COSC602_P4_ExpressionOutput.txt");// output file

		@SuppressWarnings("resource")
		FileWriter out = new FileWriter(outputFile);// The output stream

		BufferedReader br = null; // used to read lines in initialFile

		int answer = 0; // This variable will hold the answer to the equation

		MyStack st = new MyStack(); // empty stack, will be used to place in the operators.

		MyDeque dQu = new MyDeque(); // empty deque, will be used to hold the numbers

		MyBinaryTreeNode rightNode; // Serve as the right tree node 

		MyBinaryTreeNode leftNode;// Serve as a the left tree node
		
		MyExpressionTree tree; //Variable where operations will be performed
		

		try {
			br = new BufferedReader(new FileReader(inputFile));// read initial file line by line
			String temp = br.readLine(); // temp read and hold each line in txt file word

			while (temp != null) { // start of while loop, go until there are no more lines to read

				temp = temp.trim(); // remove leading and trailing whitespace
				if (temp.length() == 0 || temp.isEmpty() || temp == "" || temp.equals("\n")) { // Here is where we skip
																								// empty lines
					temp = br.readLine();
					continue;// push forward
				}
				out.write("original expression: " + temp + "\n");

				boolean isValidExpression = true; // will be utilized to check if the expression in the input file is a
													// valid expression

				for (int i = 0; i < temp.length(); i++) { // this is the loop that will go through an expression

					if (temp.charAt(i) == ' ') { // Ignore white space
						continue;
					}
					if (i < temp.length() - 1 && Character.isDigit(temp.charAt(i))
							&& Character.isDigit(temp.charAt(i + 1))) { // check for two digits within the length of
																		// temp
						isValidExpression = false;
						out.write("Not Valid" + "\n");
					}

					// The below are conditions that make the expression not valid
					if (temp.charAt(temp.length() - 1) == '+') {
						isValidExpression = false;
						out.write("Not Valid." + " \n");
						break;
					}
					if (temp.charAt(temp.length() - 1) == '-') {
						isValidExpression = false;
						out.write("Not Valid." + " \n");
						break;
					}
					if (temp.charAt(temp.length() - 1) == '*') {
						isValidExpression = false;
						out.write("Not Valid." + " \n");
						break;
					}
					if (temp.charAt(temp.length() - 1) == '/') {
						isValidExpression = false;
						out.write("Not Valid." + " \n");
						break;
					}
					if (temp.charAt(temp.length() - 1) == '%') {
						isValidExpression = false;
						out.write("Not Valid." + " \n");
						break;
					}

					if (Character.isDigit(temp.charAt(i))) {// put digit into deque
						dQu.insertBack(new MyBinaryTreeNode(temp.charAt(i)));
						

					}

					else if (temp.charAt(i) == '(') { // if we find an open parentheses, automatically push onto stack
						
						st.push(temp.charAt(i));

					}

					else if (temp.charAt(i) == ')') {

						if (st.isEmpty()) { // If the stack is empty, since we are at charAt(')'); that means there is
											// no open parentheses
							isValidExpression = false;
							out.write("Not Valid." + "\n");
							break;
						} else {// pop operators off of the stack UNTIL we hit another open (, or until empty...
								// (if empty, throw Invalid message) see above

							while (!st.isEmpty() && ((Character) st.top() != '(')) {
								
								rightNode = (MyBinaryTreeNode) dQu.removeBack();// right node
								leftNode = (MyBinaryTreeNode) dQu.removeBack();// left node
							    dQu.insertBack(new MyBinaryTreeNode(st.pop(), leftNode, rightNode));// parent node (corresponding last operator) with left and right ints
							    
							}
							if (st.isEmpty()) {
								isValidExpression = false;
								out.write("Not Valid." + "\n");
								break;
							}
							if (!st.isEmpty() && ((Character) st.top() == '(')) {
								
								new MyBinaryTreeNode (st.pop());//Should all st.pop be turned into a new MyBinaryTreeNode?
							}
						}
					}

					else { 

						if (!st.isEmpty() && orderOfOp(temp.charAt(i)) > orderOfOp((char) st.top())) {
							
							st.push(temp.charAt(i));
						} else {
							while (!st.isEmpty() && orderOfOp(temp.charAt(i)) <= orderOfOp((char) st.top())) {
								
								rightNode = (MyBinaryTreeNode) dQu.removeBack();// right node
								leftNode = (MyBinaryTreeNode) dQu.removeBack();// left node
								dQu.insertBack(new MyBinaryTreeNode(st.pop(), leftNode, rightNode));// parent node with left and right
							    
							}
							
							st.push(temp.charAt(i));					
						}

					}
					
					
				} // This is the end of the for loop
				
				
				while (!st.isEmpty() && ((Character) st.top() != '(')) {
					
					rightNode = (MyBinaryTreeNode) dQu.removeBack();// left node
					leftNode = (MyBinaryTreeNode) dQu.removeBack();// right node
					dQu.insertBack(new MyBinaryTreeNode(st.pop(), leftNode, rightNode));// parent node with left and right
				}
			
				if (!st.isEmpty() && ((Character) st.top() == '(')) {
					isValidExpression = false;
					out.write("Not a valid expression" + "\n");
					break;
				} 
				if (isValidExpression) {
					
					
					tree = new MyExpressionTree ((MyBinaryTreeNode)dQu.back());//This should allow me to cast to a MyExpressionTree and perform operations
					
					out.write("Pre-Order: " + tree.preorderTraversal());
					out.write("In-Order: " + tree.inorderTraversal());
					out.write("Post-Order: " + tree.postorderTraversal());
					answer = tree.evaluate((MyBinaryTreeNode)dQu.back());
					out.write("Evaluation: " + answer + "\n" + "\n");
					 
				}
				
				temp = br.readLine();
				
				
				
		}
		} finally {
			br.close();
			out.close();
		}
		System.out.print("File closed");
	}

}
