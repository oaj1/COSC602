package project;

import java.io.*;
import java.util.*;

import structure.*;

/**
 * This class will handle the input.txt doc comprised of digits and operators
 * and perform the operation using the MyQueue and MyStack classes. If the
 * operation is not valid, it will return a not valid operation method
 * 
 * @author Oliver James
 *
 */
public class COSC602_P3_InfixToPostfix {

	/**
	 * 
	 * @param c, char variable representing the operator
	 * @return numeric value, representing the order of operation, higher number is
	 *         higher order of operation
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

		File outputFile = new File("../COSC602_P3_Output.txt"); // The file where postfix and/or answer will be written

		String initial = "../COSC602_P3_InfixInput.txt";// initial will hold the infix expressions to do operations on

		FileWriter out = new FileWriter(outputFile);// The output stream

		BufferedReader br = null; // used to read lines in initial
		
		int answer = 0; // This variable will hold the answer to the equation

		int left, right; // corresponds to the left int to use and right int to use on the operation

		MyStack st = new MyStack(); // empty stack, will be used to place in the numbers and
									// operators.
		MyQueue qu = new MyQueue(); // empty queue, will be used to hold the numbers and
									// operators

		try {

			br = new BufferedReader(new FileReader(initial));// read initial file line by line
			String temp = br.readLine(); // temp read and hold each line in txt file word

			while (temp != null) { // start of while loop, go until there are no more lines to read

				temp = temp.trim(); // remove leading and trailing whitespace
				if (temp.length() == 0 || temp.isEmpty() || temp == "" || temp.equals("\n")) { // Here is where we skip
																								// empty lines
					temp = br.readLine();
					continue;// push forward
				}

				out.write("original expression: " + temp + "\n"); // output onto the output file; is should print the
																	// expression

				boolean isValidExpression = true;

				for (int i = 0; i < temp.length(); i++) { // this is the loop that will go through an expression

					if (temp.charAt(i) == ' ') { // Ignore white space
						continue;
					}
					
					  if (i < temp.length()-1 && Character.isDigit(temp.charAt(i)) && Character.isDigit(temp.charAt(i+1))){ //check for two digits within the length of temp
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

					if (Character.isDigit(temp.charAt(i))) {// add isDigit values to qu, but LATER convert to numbers
						qu.insertBack(temp.charAt(i));

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
								qu.insertBack(st.pop());// insert popped off operators into qu until an open parentheses
														// is met, or until empty
							}
							if (st.isEmpty()) {
								isValidExpression = false;
								out.write("Not Valid." + "\n");
								break;
							}
							if (!st.isEmpty() && ((Character) st.top() == '(')) {
								st.pop();
							}
						}
					}

					else { // Below is where I'm dealing with operators
						
						if (!st.isEmpty() && orderOfOp(temp.charAt(i)) > orderOfOp((char) st.top())) {
							st.push(temp.charAt(i));// push higher value operator onto the stack

						} else {
							while (!st.isEmpty() && orderOfOp(temp.charAt(i)) <= orderOfOp((char) st.top())) {

								qu.insertBack(st.pop()); // if the order of op on the current stack is larger, then we
															// are putting the current one onto qu

							}
							st.push(temp.charAt(i));// Now push the lower priority one onto the stack, since we popped
													// the higher one off

						}

					}
					

				} // This is the end of the for loop

				// br.readLine();
				while (!st.isEmpty() && ((Character) st.top() != '(')) {
					qu.insertBack(st.pop());// insert popped off operators into qu until an open parentheses is met, or
											// until empty
				}

				if (!st.isEmpty() && ((Character) st.top() == '(')) {
					isValidExpression = false;
					out.write("Not a valid expression" + "\n");
					break;
				}
				if (isValidExpression) {

					out.write(qu.toString());
				}

				while (!qu.isEmpty()) {
					if (Character.isDigit((Character) qu.front())) {

						st.push((Character) qu.removeFront() - '0');// The - 0, pushes the VALUE versus the character
					} else if ((Character) qu.front() == '*') {
						right = (int) st.pop();
						left = (int) st.pop();

						answer = left * right;
						qu.removeFront();// remove the * the front of the que
						st.push(answer);
					} else if ((Character) qu.front() == '/') {
						right = (int) st.pop();
						left = (int) st.pop();

						answer = left / right;
						qu.removeFront();// remove the / the front of the que
						st.push(answer);
					} else if ((Character) qu.front() == '%') {
						right = (int) st.pop();
						left = (int) st.pop();

						answer = left % right;
						qu.removeFront();// remove the % the front of the que
						st.push(answer);
					}

					else if ((Character) qu.front() == '+') {
						right = (int) st.pop();
						left = (int) st.pop();

						answer = left + right;
						qu.removeFront();// remove the + the front of the que
						st.push(answer);
					} else if ((Character) qu.front() == '-') {
						right = (int) st.pop();
						left = (int) st.pop();

						answer = left - right;
						qu.removeFront();// remove the - the front of the que
						st.push(answer);
					}
				}
				if (isValidExpression) {
					out.write("answer: " + answer + "\n");
				}
				st.clear();// Create new stack
				temp = br.readLine();
				

			}

		} catch (IOException e) {
			System.out.println("Error with file"); // catch message
			e.printStackTrace();

		} finally {

			br.close();// close the buffered reader
			out.close();// close the output file once program has finished running
		}

		System.out.println("File Closed");

	}
}
