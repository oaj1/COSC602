
package structure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyExpressionTree extends MyBinaryTree {

	//private FileWriter out;// The output stream
	private String toAppend;

	public MyExpressionTree() {
		root = null;

	}

	public MyExpressionTree(MyBinaryTreeNode rt) throws IOException {
		root = rt;
		//out = new FileWriter(f);
	}

	/**
	 * This is a static method to evaluate any expression tree by passing in its
	 * root
	 * 
	 * @param rt, the root of the expression tree to be evaluated @return, the
	 *            integer value of the evaluated expression tree
	 */

	public static int evaluate(MyBinaryTreeNode rt) {
		if (rt == null)
			return -1;

		if (rt.left == null && rt.right == null)
			return Character.getNumericValue((Character) rt.data);

		switch (rt.data.toString()) {
		case "-":
			return evaluate(rt.left) - evaluate(rt.right);
		case "+":
			return evaluate(rt.left) + evaluate(rt.right);
		case "*":
			return evaluate(rt.left) * evaluate(rt.right);
		case "/":
			return evaluate(rt.left) / evaluate(rt.right);
		case "%":
			return evaluate(rt.left) % evaluate(rt.right);
		}
		return -1;

	}

	/**
	 * This is the class where I will override some of the MyBinaryTree class
	 * methods
	 */

	/**
	 * Preorder traverse the tree and print out each node.
	 */
	public String preorderTraversal() {// return type of a String is for subclass to override this method and return a
		toAppend = "";								// String.
		
			try {
				preorderHelper(root);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			toAppend += "\n";
		
		
		return toAppend;
	}

	/*
	 * The private helper method for preorderTraversal().
	 */
	private void preorderHelper(MyBinaryTreeNode rt) throws IOException {
		if (rt == null)
			return;
		toAppend += "\t" + rt.data.toString();
		//System.out.print("\t" + rt.data.toString());
		//out.write("\t" + rt.data.toString());
		preorderHelper(rt.left);
		preorderHelper(rt.right);
	}

	/**
	 * Inorder traverse the tree and print out each node.
	 */
	public String inorderTraversal() {// return type of a String is for subclass to override this method and return a
										// String.
		toAppend = "";								// String.
		
			inorderHelper(root);
			toAppend += "\n";
			return toAppend;
	}

	/*
	 * The private helper method for inorderTraversal().
	 */
	private void inorderHelper(MyBinaryTreeNode rt) {
		if (rt == null)
			return;
		
		
		
		inorderHelper(rt.left);
		toAppend += "\t" + rt.data.toString();
		inorderHelper(rt.right);
		
	}

	/**
	 * Postorder traverse the tree and print out each node.
	 */
	public String postorderTraversal() {// return type of a String is for subclass to override this method and return a String
		
		
		toAppend = "";								// String.
		
		postorderHelper(root);
		toAppend += "\n";
		return toAppend;
	}

	/*
	 * The private helper method for postorderTraversal().
	 */
	private void postorderHelper(MyBinaryTreeNode rt) {
		if (rt == null)
			return;

		
		postorderHelper(rt.left);
		postorderHelper(rt.right);
		toAppend += "\t" + rt.data.toString();}

}
