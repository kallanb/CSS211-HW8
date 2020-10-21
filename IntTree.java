package hw08;
/*
Kallan Brainard 
CS 211
August 9, 2020
 */

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal.  The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."

import java.util.*;

public class IntTree {
	private IntTreeNode overallRoot;

	// pre : max > 0
	// post: constructs a sequential tree with given number of
	//       nodes
	public IntTree(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("max: " + max);
		}
		overallRoot = buildTree(1, max);
	}

	// post: returns a sequential tree with n as its root unless
	//       n is greater than max, in which case it returns an
	//       empty tree
	private IntTreeNode buildTree(int n, int max) {
		if (n > max) {
			return null;
		} else {
			return new IntTreeNode(n, buildTree(2 * n, max),
					buildTree(2 * n + 1, max));
		}
	}

	// post: prints the tree contents using a preorder traversal
	public void printPreorder() {
		System.out.print("preorder:");
		printPreorder(overallRoot);
		System.out.println();
	}

	// post: prints the tree contents using a preorder traversal
	// post: prints in preorder the tree with given root
	private void printPreorder(IntTreeNode root) {
		if (root != null) {
			System.out.print(" " + root.data);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	// post: prints the tree contents using a inorder traversal
	public void printInorder() {
		System.out.print("inorder:");
		printInorder(overallRoot);
		System.out.println();
	}

	// post: prints in inorder the tree with given root
	private void printInorder(IntTreeNode root) {
		if (root != null) {
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}

	// post: prints the tree contents using a postorder traversal
	public void printPostorder() {
		System.out.print("postorder:");
		printPostorder(overallRoot);
		System.out.println();
	}

	// post: prints in postorder the tree with given root
	private void printPostorder(IntTreeNode root) {
		if (root != null) {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.print(" " + root.data);
		}
	}

	// post: prints the tree contents, one per line, following an
	//       inorder traversal and using indentation to indicate
	//       node depth; prints right to left so that it looks
	//       correct when the output is rotated.
	public void printSideways() {
		printSideways(overallRoot, 0);
	}

	// post: prints in reversed preorder the tree with given
	//       root, indenting each line to the given level
	private void printSideways(IntTreeNode root, int level) {
		if (root != null) {
			printSideways(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.data);
			printSideways(root.left, level + 1);
		}
	}

	/* Exercise 1. 
	 * Write a method called countLeftNodes that returns the number of left children
	 * in the tree. A left child is a node that appears as the root of the left-hand
	 * subtree of another node. For example, reference tree #1 has 3 left children
	 * (the nodes storing the values 5, 1, and 4). */
	public int countLeftNodes () {
		return countLeftNodes(overallRoot);
	}

	private int countLeftNodes (IntTreeNode root) {
		int count = 0;

		if (root == null) {
			return 0; 
		}

		if(root.left != null) {
			count += 1 + countLeftNodes(root.left);  // update when left node
		}

		if(root.right != null) {
			count += countLeftNodes(root.right);
		}
		return count;
	} 

	/* Exercise 5
	 * Write a method called printLevel that accepts an integer parameter n and
	 * prints the values at level n from left to right, one per line. We will use
	 * the convention that the overall root is at level 1, its children are at level
	 * 2, and so on. If there are no values at the level, your method should produce
	 * no output. Your method should throw an IllegalArgumentException if it is
	 * passed a value for a level that is less than 1. For example, if a variable t
	 * refers to reference tree #2, then the call of t.printLevel(3); would produce
	 * the following output: 0 7 6 */
	public void printLevel (int n) {
		printLevel(overallRoot, n);
	}

	private void printLevel (IntTreeNode root, int n) {
		if (root == null) {
			return; //produce no output
		} else if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 1) {
			System.out.println(root.data);
		} else {
			printLevel(root.left, n - 1);
			printLevel(root.right, n - 1); 
		}
	}

	/* Exercise 8
	 * Write a toString method for a binary tree of integers. The method should
	 * return "empty" for an empty tree. For a leaf node, it should return the data
	 * in the node as a string. For a branch node, it should return a parenthesized
	 * String that has three elements separated by commas: the data at the root, a
	 * string representation of the left subtree, and then a string representation
	 * of the right subtree. For example, if a variable t refers to reference tree
	 * #2, then the call t.toString() should return the following String (without
	 * the surrounding quotes):
	 * "(2, (8, 0, empty), (1, (7, 4, empty), (6, empty, 9)))" */
	public String toString() {
		return toString(overallRoot);
	}

	private String toString (IntTreeNode root) {
		String str = "";
		if (root == null) {
			str = "empty";
		} else {
			str = str + root.data;
			if (root.left != null || root.right != null) {
				str = "(" + str + ", " + toString(root.left);
				str = str + ", " + toString(root.right) + ")";
			}
		}
		return str;
	}
}